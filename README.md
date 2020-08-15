# *Wikidata* Explorer

*Wikidata* Explorer is a simple interface to the world of
structured data and knowledge graphs.

This application was made for a UBC CPSC 210 project so don't expect it to be
 well coded. That's also why there are many things that may seem odd because
  they were requirements for the class project which I could not change. Feel
  free to
  use it however you wish!

## Introduction

*Wikidata* Explorer allows the user to see the relationships
between information about the world. The application is rather
simple, You can select a concept, property, etc. to start with
(or use the default concept). From there, basic information is
shown along with important properties. You can also expand the
properties list to show all of them or search for a specific
one. If a property interests you, then you can pull its card
out to the side and explore additional information.

This application is of interest to anyone who is interested in
structured data, computational intelligence, or just finds the
relationships between things fascinating. This application
provides an overview of *Wikidata* that allows a better
understanding of the relationships which are the core of
*Wikidata*. It's helpful for those interested in using the
*Wikidata* API as it allows you to take a glimpse into the
structure which makes it much easier to determine how to best
utilize the data.

I have been fascinated with *Wikidata* for a long time. It is incredibly
 interesting how concepts,
properties, lexemes, and other data-types are related to
each-other. I keep a list of ideas for coding projects on my
phone so that I always have once when I need it. Many of the
ideas don't work in Java or don't fit the requirements of this
project (I mostly make cli tools). It just happened that
this one (item number 10 on my list) does work which is why I
decided to work on it this time.

## *Wikidata* Basics
*Wikidata* stores a collection of structured data, that means that every piece
of data is linked to other pieces of data. The full format is rather complex, so
I will just cover the basics. Any "concept" has a concept ID starting with a
**Q**. For example, **Q2** is **Earth**, and **Q42** is **Douglas Adams** (the
author) every concept has a label which may optionally be in multiple languages.
**Q2** has the label "Earth" in English, "Terre" in french, "Terra" in Italian,
etc. Each language also has a description, and a list of alternative names such
as "Pale Blue Dot" in English and "pianeta azzurro" in Italian. There is then a
list of statements. Each statement consists of a property (which has an ID
starting with a **P**), a list of values for that property, an optional list of
qualifiers for each value, and a list of references (each reference itself
having properties and values) for each value. Every property also has its own
label, description, etc. describing it and often an associated concept.

It gets a lot more complex than this. There are many other datatypes, properties
and relationships which can be encoded with this system. I won't get into all of
it but one of the nice things about this system is that it allows you to take
human language statements and turn them into values that you can look up. For
example, the English statement:

* "Mount Everest is the highest mountain in the
world"

can be transformed into the statement:

* "**Earth**
(<sub><sup>**Q2**</sup></sub>)
(<sub><sup>item</sup></sub>) → **highest point**
(<sub><sup>**P610**</sup></sub>) (<sub><sup>property</sup></sub>) →
**Mount Everest** (<sub><sup>**Q513**</sup></sub>)
(<sub><sup>value</sup></sub>)"

or something even simpler for computers to
understand like

* "**Q2** → **P610** → **Q513**".

While Wikidata itself may not
be used for all of these applications, it is this kind of data and statement
system that powers common assistants like Google Assistant, and Siri. That's why
I was interested in making this program, many people may wish to learn about
Wikidata's structured data system to see if they can incorporate it into their
own projects and systems so this program is a good way to get an overview to see
how data is organized.

## User stories
* As a user, I want to be able to query information about an entity.
* As a user, I want to view statements and save those which I am interested in.
* As a user, I want to add multiple downstream entities to my view.
* As a user, I want to search for an entity by name.
* As a user, I want to ask simple questions and validate statements.

# Usage

## CLI

* Clone the latest version of the repository. There are a ton of bugs and
 other issues which I fix as I figure them out, so it's best to be on the
  latest version.
* Open the IntelliJ project and build the project.
* Navigate in a terminal emulator to the root of the project (if your
 terminal emulator does not support ANSI escape sequences, you will need to
  add `IntelliJ` as an argument to the program to use a fixed screen size).
  A listing of the directory should look as follows:
```
Project-Starter.iml		checkstyle.xml			project_x3v2b.plantuml
Project-Starter.plantuml	data				project_x3v2b.png
README.md			lib				src
UIDesign.png			out
```
* Figure out which version of Java to use. This is build with Corretto 8 bt
 should work with any version of Java 8. (This is most important if you have
  many versions of Java installed).
  On my computer, this is 
`/Library/Java/JavaVirtualMachines/amazon-corretto-8.jdk/Contents/Home/bin/java`
* Determine the required classpath, 
`-classpath out/production/Project-Starter/:lib/gson-2.8.6.jar` seems to work
 well for me but may need more added depending on what is already in the
  classpath. The `lib` folder is the first place to look for a missing class.
* Determine the required arguments. There are a few supported. The most
 important one is `web` which specifies that you want to include information
  from the *Wikidata* website rather than just the local cache. Using web
   information is much slower but avoids many possible null pointer issues
    which I have not added proper error handling for yet. The other important
     one which I mentioned before is `IntelliJ` which must be added if you
      are using a terminal without ANSI escape sequences. This includes the
       built in terminal to IntelliJ but also the default Windows `cmd.exe`.
* Actually run the program! Your command will be built using the previous
 steps. Mine looks like this:
 `/Library/Java/JavaVirtualMachines/amazon-corretto-8.jdk/Contents/Home/bin/java
 -classpath out/production/Project-Starter/:lib/gson-2.8.6.jar ui.CLInterface
 web`
* Unless you had the IntelliJ option enabled, you will see some weird
 characters printed out. I see `[[70;114R` for example. That shows that my
  terminal is 70 columns wide by 114 lines tall. This is because of the
   secret way that the program determins the size of the screen. You never
    see this, but it prints out `\u001b[s\u001b[5000;5000H\u001b[6n\u001b[u
    `. This does the following:
```
"\u001b[s"             save cursor position
"\u001b[5000;5000H"    move to col 5000 row 5000
"\u001b[6n"            request cursor position
"\u001b[u"             restore cursor position
```
* This basically moved the cursor far away which the console can't do, so
 when the position is read, it finds the actual bounds of the console. The
  cursor is then put back as to not mess up the display. This is the best way
   that I have found to do this to work on most platforms. There are other
    methods to find the console size but none work well on multiple platforms
    . Usually, the program would just see the cursor position `[[70;114R` as
     input to standard in. Java is weird though and prints it out like you
      had typed it as user input. So you then just have to press enter to
       actually input that to the program. It's weird, but it is important
        you press enter and do not type anything else.
* Now you should be able to get to actually using the program! I recommend
 typing load to load the cache, so you don't have to wait to gather all the
  web data. You can type exit at any time to exit the program. The starting
 entry is for Douglas Adams. It shows ten of the statements about him. To
  view a statement, type his id (Q42), then the statement you are interested in.
  So to look at his mother, type `Q42 P25`. If you are not interested in an
   entry, add an `R` after. So if you don't care about his spouse, type `Q42
    P26 R` to remove it from the list. If you know the property number for
     something, you can add it to the list using the search bar. To add
      notable works, type `Q42 S P800`.
* To learn more about a property, type its ID when it is open, so if you had
 `P25` opened, type `P25` to have the page about that specific property open
 . If you are interested in teh subject of a statement, type the property
  then the subject ID. So if you had `P25` open, you can type `P25 Q14623678
  `. This will shows you the specific statement (since one property can have
   multiple statements associated, see `P800`). This page will then show you
    the refrences and qualifiers for that statement. These cannot be opened
     further. Now that this page is open, you can see the general page by
      typing in the ID. So to see the general page about Janet Adams, type 
      `Q14623678`. Note that anything you type which opens something can be
       typed again to close it. But be careful not to close things in the
        wrong order, or it may become impossible to close something further
         down the chain.
* If you are not interested in Douglas Adams, you can use the general search
 overall to look for something else. For example, type `S Q2` to learn about
  Earth, or `S Q76` to learn about Barack Obama. Or even `S Q14527788` to
   learn about the birth certificate of Barack Obama.
* You can also type save to save anything which needed to be loaded from the
 web to the local cache file. This way, it can be used without being loaded
  from the web again next type you open the program and type load.
* The question is, how do I find these IDs? I've memorized a few to use as
 examples, but I don't have a way to search by words (yet . . .). All you can
  do is go to [*Wikidata*](https://www.wikidata.org/wiki/Wikidata:Main_Page
  ) iself and search for what you want and look at the URL or title to find
   the ID. You can also use the Wikidata pages to find properties that might
    be interested. The point of this program is not to help you actually find
     the information, but to help you see the connections between data.

## GUI

* To use the GUI, you can easily just run GUInterface from IntelliJ.
* Drag a panel to move it around.
* Drag outside of a panel to move everything.
* Click save or load to save or load the local cache.
* Type something in the search bar at the top and press enter to load up that
 ID.
* Don't ask why the colors don't quite work (It is not easy to get buttons
 and JMenuBars to change their color).
* Press X on a panel to close it.
* Press the right arrow on a panel to open another panel with that item's ID
. (Similar to just typing an ID on its own in the CLI version).
* Use the search bar on an item to search for a property by ID.
* Click the arrows to view more information about that property or statement.
* Be sure to click on one of the arrows next to image to see a photo of that
 item. I recommend searching for Q76 (Barack Obama) and clicking on his arrow
  for the image. There are quite a few other good ones too, I also like the image
   for Earch (Q2). Currently, this only shows one image, but I am considering
    finding a way to view all images as some items have multiple.
* It's fun just to play around and look for connections! I recommend that if
 you run out of space, start going upwards, there are some weird issues with
  jumping around when you go down then add a new panel. I have spent hours
   debugging it but I have absolutely no idea. It may be an issue with the
    DragLayout which I had to find on StackOverflow. Swing is really annoying
     to use, so I hope it is not too big a deal that I had to borrow this one
      bit from the internet. I added a comment with a link to the original
       location. I could have maybe figured that part out own my own, but it
        would have taken many more hours than I was willing to put in.
* That's about all there is to it!

# Phase 4

## Task 2

* Test and design a class that is robust: I have an Exception called
 NotFoundException. This is mainly thrown in the WebCollector and
  LocalCollector classes if data for a specific ID could not be found. This
   exception is then caught in a number of different classes. In some cases
   , there is nothing that can be done, so an unchecked exception is thrown
    because there is no easy way for the program to continue. Having this
     exception does allow for may of the NullPointerExceptions which happened
      in the past to no longer happen however, so it's overall quite good.
* Include a type hierarchy: I have a very complex hierarchy in multiple
 locations. The main one is the Value hierarchy. A Value is any datatype on
  *Wikidata*. A Datum extends it and is some Value which has a *Wikidata* URI
  . There are further subclasses of Datum for the different types of pages
  . There is also the additional package which contains many datatypes which
   do not have their own URI. There are even more subclasses for specific
    uses. It's quite a complex hierarchy with many classes. Maybe
     if I actually document my code someday, it will be possible to fully
      understand what is going on. At this point, even I couldn't fully
       explain what is going on.
* Make appropriate use of the Map interface: Besides the specific JSON cases
 where I need a Map, I use one for my cache of data collected from the web in
  this session in my WebCollector class. I also use it for the lists of
   statements, qualifiers, and references, so I can look them up by ID.
* Make appropriate use of a bi-directional association somewhere in your code
: There are a number of places where I have bi-directional associations. I
 can't possibly list all of them, but if you want to see one of them, you can
  look at the LayoutManager and ItemViewController classes.

## Task 3
At this point, there is not much I can do to improve the structure and
 design without spending a huge number of hours. There are a lot of classes
  that both handle data and display of that data. There are also a lot of
   places where things not existing cause problems which I cannot easily fix
   . Overall, there really just needs to be a much better design to the
    whole thing which more clearly separates what each thing does. Statements
     and DatumLink and a number of other classes shouldn't really be items
      but are because when I wrote them, making them not items would require
       a lot of changes elsewhere. There could be a much better use of the
        composite pattern than the not-so-great usage that I have now which
         only somewhat works. Have a look at model.uml and Dependencies.uml
          (for dependencies.uml, select all and click show dependencies to see
           the craziness). Then you will understand how hard changing
            literally anything is. Basically, my entire project has extremely
             high coupling which is quite bad if I ever want to expand it
             . That being said, I will probably never do anything else with
              this project. If I ever wanted to do it again, I would write it
               as a web app. There are a number of nice JavaScript libraries
                that let you use Python rather than JS for client-side
                 implementations. I've also updated the project_x3v2b UML
                  diagram which is created by SketchIT and plantUML. It seems
                   to not really work properly but is another nice
                   visualization.
