class Archetype:
	def __init__(self, name = "", code = "0x0"):
		self.name = name #Archetype name
		self._set_code(code) #Setcode
		self.subs = list() #Sub-archetypes
		
	def _get_code(self):
		return self._code
		
	def _set_code(self, code):
		if not(code[0] == '0' and code[1] == 'x'):
			code = "0x" + code
		self._code = code
		
	code = property(_get_code,_set_code)
	
	def add_sub(self, sub):
		if not sub in self.subs:
			self.subs.append(sub)
		else:
			print("{} is already a sub-archetype of {}".format(sub,self.name))
			
	def remove_sub(self, sub):
		if sub in self.subs:
			del_index = self.subs.index(sub)
			del self.subs[del_index]
		else:
			print("{} is not a sub-archetype of {}".format(sub, self.name))
	
	def __str__(self):
		arch = "Setname: " + self.name + "\t Setcode: " + self.code + '\n'
		if len(self.subs) > 0:
			arch += "Sub-archetypes: "
			for i in self.subs:
				arch += i + " "
		return arch
		
	def __repr__(self):
		return self.__str__()
