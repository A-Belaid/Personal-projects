import sys, getopt
from util.ArchetypeList import ArchetypeList
from pickle import Pickler, Unpickler

def main(argv):
	"""
	This script allows the management of archetype and their setcodes
	Options:
		Add a new archetype: -w, --new --name <name> -s, --setcode <setcode>
		Delete an archetype: -d, --delete <name>
		Edit an archetype: -e, --edit <name> -n, --name <new name>
		Import archetypes from text file: -i --import <import file name>
		Load backup sets file: -b --backup
		Export archetypes into text file: -x --export <export file name>
		View an archetype: -v, --view <name>
		View all archetypes: -a, --all
	"""
	
	help = main.__doc__
	
	mode = ""
	target = ""
	setcode = ""
	new_name = ""
	
	try:
		opts, args = getopt.getopt(argv, "hw:s:d:e:n:i:bx:v:a",
		["help", "new=", "setcode=", "delete=", "edit=", "name=", "import=", "backup", "export=", "view=", "all"])
	except getopt.GetoptError:
		print(help)
		sys.exit(2)
	
	try:
		fichier = open("util/sets", "rb")
		unpick = Unpickler(fichier)
		sets = unpick.load()
		fichier.close()
		if len(sets) > 0:
			with open("util/sets_backup", "wb") as backup_file:
				pick = Pickler(backup_file)
				pick.dump(sets)
	except FileNotFoundError:
		fichier = open("util/sets", "wb")
		sets = ArchetypeList()
		sets.add_archetype("Placeholder", "0x0")
		fichier.close()
		
	print("opts: ", opts)
	
	for opt, arg in opts:
		if opt in ("-h","--help"):
			print(help)
			sys.exit()
		elif opt in ("-w", "--new"):
			mode = "new"
			target = arg
		elif opt in ("-d", "--delete"):
			mode = "delete"
			target = arg
		elif opt in ("-e", "--edit"):
			mode = "edit"
			target = arg
		elif opt in ("-i", "--import"):
			mode = "import"
			target = arg
		elif opt in ("-b", "--backup"):
			mode = "backup"
		elif opt in ("-x", "--export"):
			mode = "export"
			target = arg
		elif opt in ("-v", "--view"):
			mode = "view"
			target = arg
		elif opt in ("-a", "--all"):
			mode = "all"
		elif opt in ("-s", "--setcode"):
			setcode = arg
		elif opt in ("-n", "--name"):
			new_name = arg
	print("mode: ", mode)
	
	if mode == "new":
		if setcode != "":
			sets.add_archetype(target, setcode)
		else:
			print("Error: Setcode missing. Use -s or --setcode to define a setcode.")
			print(help)
			sys.exit(2)
	elif mode == "delete":
		sets.remove_archetype(target)
	elif mode == "edit":
		if new_name != "":
			print("Error: New archetype name missing. Use -n or --new to define a new name for the archetype.")
			print(help)
			sys.exit(2)
		else:
			sets.get_archetype_by_name(target).set_archetype_name(new_name)
	elif mode == "import":
		sets.import_from_file(target)
	elif mode == "export":
		sets.export_to_file(target)
	elif mode == "backup":
		try:
			with open("util/sets_backup", "rb") as backup_file:
				unpick = Unpickler(backup_file)
				sets = unpick.load()
		except FileNotFoundError:
			print("No backup file was found.")
			sys.exit(2)
	elif mode == "view":
		print(sets.get_archetype_by_name(target))
	elif mode == "all":
		print(sets)
	else:
		print(help)
		sys.exit(2)
		
	with open("util/sets", "wb") as fichier:
		pick = Pickler(fichier)
		pick.dump(sets)
		
	
	
if __name__ == "__main__":
	main(sys.argv[1:])