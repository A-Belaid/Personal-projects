import sys, getopt, util.functions
from util.ArchetypeList import ArchetypeList
from pickle import Pickler, Unpickler

def main(argv):
	"""
	This script writes SQL queries for YGOPro programs, by reading cards' informations in a given text file
	For more information on how to write a card's information in 
	
	Options:
		Create SQL Lines:
			-i, --input <input file>
			-o, --output <output file> (optional. Default output file is YGO_SQLs.sql)
		View Categories:
			-c --categories
		Check if an archetype is defined:
			-v --view <archetype name or setcode>
	"""
	
	help = main.__doc__
	
	input = ""
	output = "YGO_SQLs.sql"
	set = ""
	
	mode = ""
	
	try:
		opts, args = getopt.getopt(argv, "hi:o:cv:", ["help", "input=", "output", "categories", "view="])
	except getopt.GetoptError:
		print("EXCEPTION!")
		print(help)
		sys.exit(0)
		
	print("opts: ", opts)
	
	for opt, arg in opts:
		if opt in ("h", "--h"):
			mode = "Help"
		if opt in ("-i", "--input"):
			mode = "Input"
			input = arg
		elif opt in ("-c", "--categories"):
			mode = "Cats"
		elif opt in ("-v", "--view"):
			mode = "Set"
			set = arg
		elif opt in ("-o", "--output"):
			output = arg
			
	print("mode: ", mode)
	
	if mode == "Help":
		print(help)
		sys.exit()
	elif mode == "Input":
		try:
			with open(input, "r") as input_file:
				cards = input_file.read()
				#Seperate cards
				cards = cards.split('%')
				cards = [card for card in cards if card != '']
				#Read cards one by one
				for card in cards:
					#Seperate card lines
					lines = card.split('\n')
					lines = [line for line in lines if line != '']
					lore_start = 4 #Line in which the lore text normally starts for Monster Cards
					#Read id, ot, alias
					id, ot, alias = util.functions.read_id(lines[0])
					#Read name
					name = lines[1]
					setcode = util.functions.read_line1(name)
					#Read attr, lv, race, type
					attr, lv, race, types = util.functions.read_line2(lines[2])
					if "Spell Card" in lines[2] or "Trap Card" in lines[2]:
						atk = defs = 0
						lore_start = 3
					else:
						if "Link" in lines[2] and "Pendulum" in lines[2]:
							atk, lv = util.functions.read_atk_link(lines[4])
							lv = util.functions.read_scale(lines[3], lv)
							lore_start = 5
						elif "Link" in lines[2]:
							atk, lv = util.functions.read_atk_link(lines[3])
							defs = -2
						elif "Pendulum" in lines[2]:
							lv = util.functions.read_scale(lines[3], lv)
							atk, defs = util.functions.read_atk_def(lines[4])
						else:
							atk, defs = util.functions.read_atk_def(lines[3])
					#Read lore text, categories(if any), and strs(if any)
					setcode, lore, cat, strs = util.functions.read_texts(lines, lore_start, setcode)
					lore = util.functions.read_lore(lore)
					if cat != "":
						cat = util.functions.read_categories(cat)
					else:
						cat = 0
					strs = util.functions.read_strs(strs)
					#Print SQL queries
					datas = util.functions.make_sql_datas(id, ot, alias, setcode, types, atk, defs, lv, race, attr, cat)
					texts = util.functions.make_sql_texts(id, name, lore, strs)
					#Write queries in output
					with open(output, "a") as output_file:
						output_file.write("--" + name + '\n')
						output_file.write(datas + '\n')
						output_file.write(texts + '\n')
		except FileNotFoundError:
			print("File {} was not found.".format(input))
			sys.exit(2)
	elif mode == "Cats":
		cat_dict = util.functions.get_cat_dict()
		for i in cat_dict:
			print(i)
	elif mode == "Set":
		try:
			with open("util/sets", "rb") as sets_file:
				sets = ArchetypeList()
				unpickle = Unpickler(sets_file)
				sets = unpickle.load()
				
				#In the case a setcode in base 10 was given as attribute
				try:
					set = int(set)
					set = hex(set)
				except ValueError:
					pass
				
				if sets.is_name_in_sets(set):
					print(sets.get_archetype_by_name(set))
				elif sets.is_code_in_sets(set):
					print(sets.get_archetype_by_code(set))
				else:
					print("Archetype or setcode {} is not defined. Please use SetManager.py to define it.".format(set))
		except FileNotFoundError:
			print("File util/sets was not found.")
			sys.exit(2)
	else:
		print(help)
		sys.exit(2)

if __name__ == "__main__":
	main(sys.argv[1:])