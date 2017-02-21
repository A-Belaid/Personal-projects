import sys
from util.ArchetypeList import ArchetypeList
from pickle import Pickler, Unpickler
from math import pow

def read_id(line): #id, ot, alias
	ot = 3 #TCG/OCG
	alias = 0
	line = line.split(", ")
	
	id = int(line[0])
	if len(line) >= 2:
		ot_dict = {"OCG": 1,
					"TCG": 2,
					"TCG/OCG": 3,
					"ANIME": 4}
		ot = ot_dict[line[1].upper()]
	if len(line) == 3:
		alias = int(line[2])
	
	return id, ot, alias

def read_line1(name): #Card name/setcodes
	try:
		with open("util/sets", "rb") as fichier:
			unpickle = Unpickler(fichier)
			sets = unpickle.load()
			sets.sort_by_names()
			sets_in_name = list()
			for i in sets:
				if i.name in name:
					sets_in_name.append(i.code)
			#Remove setcodes from the list if a sub-archetype is also there
			sets_to_remove = list()
			for i in sets_in_name:
				for j in sets.get_archetype_by_code(i).subs:
					if sets.get_archetype_by_name(j).code in sets_in_name and i not in sets_to_remove:
						sets_to_remove.append(i)
			for i in sets_to_remove:
				del_index = sets_in_name.index(i)
				del sets_in_name[del_index]
			#Calculate output setcode
			total_setcode = 0
			for i in sets_in_name:
				total_setcode += int(i, 0) * pow(16, 4 * sets_in_name.index(i))
			return int(total_setcode)
	except FileNotFoundError:
		return 0
	
def read_fixed_setcode(fixed_sets):
	#If "None", the card has no archetypes
	if fixed_sets == "None":
		return 0
	new_setcode = 0
	new_sets = list()
	try:
		with open("util/sets", "rb") as set_file:
			unpick = Unpickler(set_file)
			sets = unpick.load()
			
			#Seperate all archetypes
			fixed_sets = fixed_sets.split(", ")
			for set in fixed_sets:
				#Check if there are new archetypes (archetypes that were given setcode in the text file)
				selected_set = set.split()
				selected_code = selected_set[len(selected_set) - 1]
				if selected_code[0] == '0' and selected_code[1] == 'x':#If selected_code is a setcode
					new_setcode *= pow(16,4) + int(selected_code, 16)
					#Recover archetype's name
					new_set_name = selected_set[0]
					for i in range(1, len(selected_set) - 1):
						new_set_name += " " + selected_set[i]
					#Register archetype's name
					new_sets.append((new_set_name, selected_code))
				else:#If selected_code was part of the archetype's name
					selected_code = sets.get_archetype_by_name(set).code
					new_setcode *= pow(16,4) + int(selected_code, 16)
		
		#Register the new archetypes
		with open("util/sets", "wb") as set_file:
			for i in new_sets:
				if not sets.is_name_in_sets(i[0]) and not sets.is_code_in_sets(i[1]):
					sets.add_archetype(i[0], i[1])
			pick = Pickler(set_file)
			pick.dump(sets)
			
		
		return new_setcode
	except FileNotFoundError:
		return 0

def read_attribute(attr):
	attr_dict = {"EARTH": 1,
				"WATER": 2,
				"FIRE": 4,
				"WIND": 8,
				"LIGHT": 16,
				"DARK": 32,
				"DIVINE": 64}
	try:
		return attr_dict[attr.upper()]
	except KeyError:
		print("Attribute {} not identified.".format(attr))
		sys.exit(2)
		
def read_race(race):
	race_dict = {"WARRIOR": 1,
				"SPELLCASTER": 2,
				"FAIRY": 4,
				"FIEND": 8,
				"ZOMBIE": 16,
				"MACHINE": 32,
				"AQUA": 64,
				"PYRO": 128,
				"ROCK": 256,
				"WINGED BEAST": 512,
				"PLANT": 1024,
				"INSECT": 2048,
				"THUNDER": 4096,
				"DRAGON": 8192,
				"BEAST": 16384,
				"BEAST-WARRIOR": 32768,
				"DINOSAUR": 65536,
				"FISH": 131072,
				"SEA SERPENT": 262144,
				"REPTILE": 524288,
				"PSYCHIC": 1048576,
				"DIVINE-BEAST": 2097152,
				"CREATOR GOD": 4194304,
				"WYRM": 8388608,
				"CYBERS": 16777216}
	try:
		return race_dict[race.upper()]
	except KeyError:
		print("Race {} not identified.".format(race))
		sys.exit(2)

def read_type(card_type):
	type_dict = {"EFFECT": 5,
				"FUSION": 6,
				"RITUAL": 7,
				"SPIRIT": 9,
				"UNION": 10,
				"GEMINI": 11,
				"TUNER": 12,
				"SYNCHRO": 13,
				"FLIP": 21,
				"TOON": 22,
				"XYZ": 23,
				"PENDULUM": 24,
				"LINK": 25}
	try:
		return pow(2, type_dict[card_type.upper()])
	except KeyError:
		print("Card Type {} not identified".format(card_type))
		sys.exit(2)
		
def read_line2_monster(line):
	lv = types = 0
	arg = 1 #to skip Lv if card is a Link Monster
	
	line = line.split('/')
	#Attribute
	attr = read_attribute(line[0])
	#Level/Rank
	if "Link" not in line:
		lv = line[1].split()
		lv = int(lv[1])
		arg += 1
	#Race
	race = read_race(line[arg])
	arg += 1
	#Types
	if arg == len(line):
		types = 17 #Normal Monster
	else:
		types_in_line = list()
		for i in range(arg, len(line)):
			types_in_line.append(line[i])
		for i in types_in_line:
			types += read_type(i)
		if ("Token" in types_in_line or "Tuner" in types_in_line or "Pendulum" in types_in_line) and len(types_in_line) == 1:
			types += 17
		else:
			types += 1
	
	return attr, lv, race, int(types)
	
def read_line2_st(line):
	st_dict = {"SPELL CARD": 2,
				"TRAP CARD": 4,
				"RITUAL SPELL CARD": 130,
				"QUICK-PLAY SPELL CARD": 65538,
				"CONTINUOUS SPELL CARD": 131074,
				"CONTINUOUS TRAP CARD": 131076,
				"EQUIP SPELL CARD": 262146,
				"FIELD SPELL CARD": 524290,
				"COUNTER TRAP CARD": 1048580}
	try:
		return st_dict[line.upper()]
	except KeyError:
		print("Card Type {} not identified.".format(line))
		
def read_line2(line): #Attribute, Lv/Rank, Race, Types
	if "SPELL CARD" in line.upper() or "TRAP CARD" in line.upper():
		return 0, 0, 0, read_line2_st(line)
	else:
		return read_line2_monster(line)
		
def read_atk_def(line):
	line = line.split('/')
	atk = int(line[0])
	defs = int(line[1])
	return atk, defs

def read_atk_link(line):
	line = line.split('-')
	atk = int(line[0])
	link = int(line[1])
	return atk, link

def read_scale(line, lv):
	line = line.split()
	scale = int(line[1])
	p_lv = scale * pow(16,6) + scale * pow(16,4) + lv #0x[Scale]0[Scale]000[Level]
	return int(p_lv)

def read_lore(lore):
	split_lore = lore.split('\"')
	fixed_lore = ""
	for i in range(0, len(split_lore) - 1):
		fixed_lore += split_lore[i] + "\"\""
	fixed_lore += split_lore[len(split_lore) - 1]
	
	split_lore = fixed_lore.split('\'')
	fixed_lore = ""
	for i in range(0, len(split_lore) - 1):
		fixed_lore += split_lore[i] + "\"\""
	fixed_lore += split_lore[len(split_lore) - 1]
	
	return fixed_lore

def read_strs(strs):
	fixed_strs = list()
	
	for i in range(0, 16):
		if i < len(strs):
			fixed_strs.append(read_lore(strs[i]))
		else:
			fixed_strs.append(" ")
	return fixed_strs

def read_texts(lines, arg, setcode):
	lore = ""
	categories = ""
	strs = list()
	
	mode = "lore"
	
	for i in range(arg, len(lines)):
		if "Archetypes: " in lines[i]:
			mode = "setcode"
		elif "Categories: " in lines[i]:
			mode = "cat"
		elif "strs:" in lines[i]:
			mode = "strs"
		
		if lines[i] != "":
			if mode == "lore":
				if i != arg:
					lore += '\n'
				lore += lines[i]
				if "Link Markers: " in lines[i]:
					lore += '\n'
			elif mode == "setcode":
				setcode_line = lines[i].split(": ")
				setcode = read_fixed_setcode(setcode_line[1])
			elif mode == "cat":
				categories = lines[i].split(": ")
				categories = categories[1]
			elif mode == "strs" and lines[i] != "strs:":
				strs.append(lines[i])
			
	return setcode, lore, categories, strs
	
	
def get_cat_dict():
	cat_dict = {"ST_DESTROY": "0x1",
			"DESTROY_MONSTER": "0x2",
			"BANISH": "0x4",
			"GRAVEYARD": "0x8",
			"BACK_TO_HAND": "0x10",
			"BACK_TO_DECK": "0x20",
			"DESTROY_HAND": "0x40",
			"DESTROY_DECK": "0x80",
			"DRAW": "0x100",
			"SEARCH": "0x200",
			"RECOVERY": "0x400",
			"POSITION": "0x800",
			"CONTROL": "0x1000",
			"CHANGE_ATK/DEF": "0x2000",
			"PIERCING": "0x4000",
			"REPEAT_ATTACK": "0x8000",
			"LIMIT_ATTACK": "0x10000",
			"DIRECT_ATTACK": "0x20000",
			"SPECIAL_SUMMON": "0x40000",
			"TOKEN": "0x80000",
			"TYPE-RELATED": "0x100000",
			"PROPERTY-RELATED": "0x200000",
			"DAMAGE_LP": "0x400000",
			"RECOVER_LP": "0x800000",
			"DESTROY": "0x1000000",
			"SELECT": "0x2000000",
			"COUNTER": "0x4000000",
			"GAMBLE": "0x8000000",
			"FUSION-RELATED": "0x10000000",
			"SYNCHRO RELATED": "0x20000000",
			"XYZ-RELATED": "0x40000000",
			"NEGATE_EFFECT": "0x80000000"}
	return cat_dict
	
def read_categories(line):
	cat = 0
	line = line.split(" + ")
	
	for i in line:
		try:
			cat += int(get_cat_dict()[i.upper()], 0)
		except KeyError:
			print("Category {} not identified.".format(i))
	
	return cat

def make_sql_datas(id, ot, alias, setcode, types, atk, defs, lv, race, attr, cat):
	datas = "INSERT OR REPLACE INTO \"datas\" VALUES ("
	datas += str(id) + ", "
	datas += str(ot) + ", "
	datas += str(alias) + ", "
	datas += str(setcode) + ", "
	datas += str(types) + ", "
	datas += str(atk) + ", "
	datas += str(defs) + ", "
	datas += str(lv) + ", "
	datas += str(race) + ", "
	datas += str(attr) + ", "
	datas += str(cat) + ");"
	
	return datas

def make_sql_texts(id, name, lore, strs):
	texts = "INSERT OR REPLACE INTO \"texts\" VALUES ("
	texts += str(id) + ", "
	texts += '\"' + read_lore(name) + "\", "
	texts += '\"' + lore + "\", "
	for i in range(0, len(strs) - 1):
		texts += '\"' + strs[i] + "\", "
	texts += '\"' + strs[len(strs) - 1] + "\");"
	
	return texts