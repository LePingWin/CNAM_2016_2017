JFLAGS = -g -encoding ISO-8859-15
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	./app/Jeu.java \
	./donnee/Bille.java \
	./donnee/Terrain.java \
	./ia/ZoneJeu.java \
	./ihm/BackgroundPanel.java \
	./ihm/PreferencesPanel.java \
	./ihm/Score.java \
	./ihm/ScorePanel.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
