JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	abilities/Abilities.java \
	items/Item.java \
	enemies/Enemy.java \
	run/PlayGame.java \
	character/Profile.java  

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
