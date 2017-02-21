from util.Archetype import Archetype
from pickle import Pickler

class ArchetypeList:
	def __init__(self):
		self.sets = list()
	
	def __iter__(self):
		return iter(self.sets)
		
	def __len__(self):
		return len(self.sets)
		
	def get_archetype_by_name(self, name):
		for i in self.sets:
			if i.name == name:
				return i
		raise NameError(name, "is not a defined archetype.")
		
	def get_archetype_by_code(self, code):
		for i in self.sets:
			if i.code == code:
				return i
		raise NameError(code, "is not a defined setcode.")
	
	def set_archetype_name(self, name, newname):
		if not self.is_name_in_sets(newname):
			self.get_archetype_by_name(name).name = newname
		else:
			print("Archetype {} is already defined".format(newname))
		
	def set_archetype_code(self, code, newcode):
		if not self.is_code_in_sets(newcode):
			self.get_archetype_by_name(name).code = newcode
		else:
			print("Setcode {} is already defined by archetype {}".format(newcode, self.get_archetype_by_code(newcode).name))
	
	def is_name_in_sets(self, name):
		for i in self.sets:
			if i.name == name:
				return True
		return False
		
	def is_code_in_sets(self, code):
		for i in self.sets:
			if i.code == code:
				return True
		return False
		
	def add_archetype(self, name, code):
		if self.is_name_in_sets(name):
			raise Exception("Archetype {} is already defined".format(name))
		if self.is_code_in_sets(code):
			raise Exception("Setcode {} is already defined by archetype {}".format(code, self.get_archetype_by_code(code).name))
		self.sets.append(Archetype(name,code))
		self.sort_by_codes()
		for i in self.sets:
			if i.name in name and name != i.name:
				self.add_sub(i.name, name)
				return
		
	def remove_archetype(self, name):
		del_index = self.sets.index(self.get_archetype_by_name(name))
		del self.sets[del_index]
		
		for i in self.sets:
			if name in i.subs:
				i.remove_sub(name)
		
	def add_sub(self, main_name, sub_name):
		self.get_archetype_by_name(main_name).add_sub(sub_name)
		
	def remove_sub(self, main_name, sub_name):
		self.get_archetype_by_name(main_name).remove_sub(sub_name)
		
	def __str__(self):
		arch = ""
		for i in self.sets:
			arch += str(i) + '\n'
		return arch
		
	def __repr__(self):
		return self.__str__()
		
	def __getstate__(self):
		if self.is_name_in_sets("Spaceholder"):
			self.remove_archetype("Spaceholder")
		return self.sets
	
	def __setstate__(self, sets):
		self.sets = sets
		
	def sort_by_codes(self):
		self.sets = sorted(self.sets, key = lambda set: int(set.code, 16))
		
	def sort_by_names(self):
		self.sets = sorted(self.sets, key = lambda set: len(set.name), reverse = True)
		
	def import_from_file(self, file):
		try:
			with open(file, "r") as import_file:
				imported_sets = import_file.read()
				imported_sets = imported_sets.split('\n')
				for set in imported_sets:
					#Isolate setcode
					set = set.split()
					selected_code = set[0]
					#Recover full name
					selected_name = ""
					for i in range(1, len(set)):
						selected_name += set[i]
						if i < len(set) - 1:
							selected_name += " "
						#Register archetype
						if not self.is_name_in_sets(selected_name) and not self.is_code_in_sets(selected_code):
							self.add_archetype(selected_name, selected_code)
			#Save changes
			with open("util/sets", "wb") as set_file:
				pick = Pickler(set_file)
				pick.dump(self)
					
		except FileNotFoundError:
			print("Could not find file", file)
			
	def export_to_file(self, file):
		with open(file, "w") as export_file:
			for i in self.sets:
				setline = i.code + " " + i.name + '\n'
				export_file.write(setline)
				
	def create_backup(self):
		with open("util/sets_backup", "wb") as backup_file:
			pic