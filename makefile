JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Class.java \
	Item.java \
	Enemy.java \
	Ability.java \
	PlayGame.java \
	Spells.java \
	Profile.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
