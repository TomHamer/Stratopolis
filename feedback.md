# Assignment Two Feedback

## Group memebers: u6044174, u6081614, u5932987

## Tutor Comment
Very smooth and intuitive UI. It could be clearer which player's turn it is.

It looks like you guys put a lot of effort into your AI designs, good job.

Your JavaFX initialisation code could've been seperated into seperated, more logical methods, as it's not always clear what each code block is initialising.

Documentation of code is widely well done; could use improvement in some sections and use of Javadoc style method level documentation.

Overall your code is quite well constructed and the game is smooth and enjoyable. Well done guys.

## Mark

### Manual mark
| Task | Description | Mark |
|:-:|---|:-:|
|8|Basic game      | 1/1 |
|9|Display scoring | .5/.5|
|11|Simple opponent| .5/.5|
|12|Good opponent  | 2/2 |

#### Manual mark 4/4


### Test results
| Task | Test | Result |
|:-:|---|:-:|
| |Compiled|1/1|
|3|TilePlacementWellFormed|5/5|
|4|PlacementWellFormed|7/7|
|6|PlacementValid|3/3|
|7|ScoreFromPlacement|1/1|
|10|GenerateMove|1/1|

#### Grade 7/7
### Deductions
| Description | Deduction |
|---|:-:|
|All files correct                | 0/-1 |
|Authorship clear for all classes | 0/-1 |
|Appropriate use of git           | 0/-1 |
|Classes compile correctly        | 0/-1 |
|Program runs from jar            | 0/-1 |
|Appropriate use of OO features   | 0/-.5 |
|Presentation pdf complete        | 0/-.5 |
|Program well designed            | 0/-.5 |
|Comments clear and sufficient    | 0/-.5 |
|Coding style good                | 0/-.5 |
|Appropriate use of JUnit tests   | 0/-.5 |
|Design and code of very high quality | 0/-.5 |
|Works well and easy to run | 0/-.5 |

#### Total deductions: 

### Final mark 11/11
## Originality statement

* The technique to implement radio buttons in Board.java was gathered from http://docs.oracle.com/javafx/2/ui_controls/radio-button.htm

* http://docs.oracle.com/javafx/2/animations/basics.htm was referenced to figure out how to do animations for Board.java

* http://stackoverflow.com/questions/26554814/javafx-updating-gui was referenced to figure out how to update UI while performing computations

* http://la4j.org (linear algebra package for neural network)

* based monte carlo on https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/ for guidence on construction and theory for this algorithm

* Assignment one music and drag and drop code

* http://stackoverflow.com/questions/27527090/finding-the-best-move-using-minmax-with-alpha-beta-pruning for inspiration with java alpha beta pruning

* The Elements of Statistical Learning (Trevor Hastie) (back and forward propagation equations)

Signed: Calum Snowdon (u6044174)

## Git Log
```
commit e49f4030942e8a4376218df3b171b39eae0bb075
Author: TomHamer <tomhamer@live.com>
Date:   Fri Oct 14 02:11:09 2016 +1100

    Final push

commit b9d046eb81793cbc02b9b51a16d76a7bcc8623cc
Author: TomHamer <tomhamer@live.com>
Date:   Fri Oct 14 02:07:21 2016 +1100

    Completed presentation!

commit 63c42bacbc72b80df7a6fcf3058adf7091823c72
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 02:02:44 2016 +1100

    Actually added new UML

commit e901478ba173be8d43d43be0a8fdb1694edf2265
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 01:59:24 2016 +1100

    Added new UML diagram

commit 4b823cb13542f57e6a67f9f280cd0554a94fbbde
Merge: 7373f5a 0f29e39
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 01:38:30 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit 7373f5ae314a4f0133b029deb98d4e69053d2201
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 01:38:21 2016 +1100

    Finalised admin details and code tidying

commit 0f29e391852b2f9180a785c90b13abb0000832e2
Merge: eab01c9 e66fbd3
Author: TomHamer <tomhamer@live.com>
Date:   Fri Oct 14 01:37:46 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit eab01c907bfeef2193211a45799841dfe9ff63ff
Author: TomHamer <tomhamer@live.com>
Date:   Fri Oct 14 01:37:36 2016 +1100

    Cleaned code

commit e66fbd346fe9ff70063e7d50a4eaf4faef60bd5a
Merge: b1a9daa b4bc094
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 01:17:54 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit b1a9daa4b84674e3129b9c0f775bd2fe93cb21f9
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 01:17:41 2016 +1100

    Synchronising repo

commit b4bc0944120092acf385744e4747579b1b3fbac7
Author: TomHamer <tomhamer@live.com>
Date:   Fri Oct 14 01:13:48 2016 +1100

    Cleaned code

commit 5eaf96c27dcd2078aeaadb09c477ce1e69075276
Merge: dd87f1c 294fe9d
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 00:26:12 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit dd87f1cdddab0673e1f19f045bf7e38fc6b84214
Author: calum <u6044174@anu.edu.au>
Date:   Fri Oct 14 00:25:18 2016 +1100

    Tidied/Commented Board.java

commit 294fe9d46c5c4f82b5cc14b339721679095d92ee
Author: TomHamer <tomhamer@live.com>
Date:   Fri Oct 14 00:06:56 2016 +1100

    PlayGames is implemented

commit 96d417e294d5efc419a493661dc3c0fe1c45d053
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 23:55:20 2016 +1100

    VCS complete

commit 7ec147956a0e6eb954728a210a70a6de51c823f8
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 23:53:45 2016 +1100

    VCS complete

commit 8b46a7e0dc165c2f575017a2a25e728eba07993c
Merge: 41904cc 037659a
Author: calum <u6044174@anu.edu.au>
Date:   Thu Oct 13 23:51:39 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit 41904ccf93a703a0f9a8c62eef23a8f5e43c8432
Author: calum <u6044174@anu.edu.au>
Date:   Thu Oct 13 23:48:43 2016 +1100

    Finished with UI implementation, polishing code

commit 037659a7ba7a718d0c95a795946fd05fd4797bb5
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 23:07:32 2016 +1100

    Ai actually works this time

commit 37700e71d223701fccd3babe126e104cef1103f0
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 22:51:46 2016 +1100

    Ai actually works this time

commit 07d7f375741381c92464990483042c3083d7a4fd
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 21:14:40 2016 +1100

    AI works for hard

commit fde2fda5835bc4a3a80b711c6d7fdb5f49ebd666
Author: calum <u6044174@anu.edu.au>
Date:   Thu Oct 13 20:42:22 2016 +1100

    Merging

commit 7e2423ceef5b9ecc7c0a730f684ac7f825c4aa17
Merge: afae15d 2fe43ac
Author: calum <u6044174@anu.edu.au>
Date:   Thu Oct 13 20:38:57 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/EasyPlayer.java
    #       src/comp1110/ass2/IntelligentPlayer.java
    #       src/comp1110/ass2/MediumPlayer.java
    #       src/comp1110/ass2/gui/Board.java

commit afae15d88ee101ce1890cf35bf130c88cf707749
Author: calum <u6044174@anu.edu.au>
Date:   Thu Oct 13 20:30:56 2016 +1100

    Updated interface, added animations

commit 2fe43ac0ada1e39e7a8486e54002e957972d979b
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 20:29:17 2016 +1100

    AI is finished

commit 786648bb66fe3c97ec18a53d88d20f079042af97
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 20:13:36 2016 +1100

    AI is finished

commit 18f10fd5a3eb63d9d2802a4b5f2b85a1f4b91c4f
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 15:31:43 2016 +1100

    Made some changes to intelligent player and hard AI

commit 8ed3be8c11b76ae70aeaa7e9286aa9c22a650d9e
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 14:17:05 2016 +1100

    Hard that cheats is done. Its kind of fast enough to be playable.

commit 40452f0b1f4e066a43105afce5a78ff5875d7114
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 14:16:11 2016 +1100

    Hard that cheats is done. Its kind of fast enough to be playable.

commit 5a7072513ccd0b8ff231a33cccf04fb26b01684f
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 01:18:06 2016 +1100

    Hard that cheats and uses alpha-beta works, but is slow

commit 089d37152732bfcd1dbcd5fb5ce91e865339594f
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 01:00:18 2016 +1100

    Medium AI debugged

commit dcae2b2e67f522a09c72e8fc740c96403ee40c54
Author: TomHamer <tomhamer@live.com>
Date:   Thu Oct 13 00:38:07 2016 +1100

    Medium AI debugged

commit 5b79258e42f76c3877fd9e0425378d0a5f6233cd
Author: TomHamer <tomhamer@live.com>
Date:   Wed Oct 12 23:33:38 2016 +1100

    Monte carlo has been implemented but is not very effective as it cannot sample enough games since the implementation is too slow

commit 0e3f5e3fd422fb0dd6aa37921873c2e9ac9993df
Merge: 4f8736d 76495b3
Author: calum <u6044174@anu.edu.au>
Date:   Tue Oct 11 19:13:02 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit 4f8736d0b1d0269002f48ca803eca87731ee38d9
Author: calum <u6044174@anu.edu.au>
Date:   Tue Oct 11 19:12:53 2016 +1100

    Updated UI, worked on cleaner methods of starting the game

commit 76495b380244a7dd5acf6961701d5bf9e327b9c3
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 11 19:09:30 2016 +1100

    Intellegent player has begun training, all AI works - optimisations still need to be made to the hard AI

commit d359f85884f14298515a48af1dba72ab457983e6
Merge: c69997b 4d2cb21
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 11 19:06:33 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit c69997b7a897cf0d0f14998998536dd29a69654d
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 11 19:06:18 2016 +1100

    Intellegent player has begun training, all AI works - optimisations still need to be made to the hard AI

commit 4d2cb21662181292c5fb3efa72cd60a1ca16cc03
Author: calum <u6044174@anu.edu.au>
Date:   Mon Oct 10 14:11:04 2016 +1100

    Fixed some warnings, minor changes

commit 409da6261a9b52271a997e33b964244c3cba62d9
Merge: 698121b 2411e6e
Author: calum <u6044174@anu.edu.au>
Date:   Mon Oct 10 14:01:33 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/NN1HL.java
    #       src/comp1110/ass2/gui/Board.java

commit 698121becff614a215ffbf57f03e801f1ed6abfb
Author: calum <u6044174@anu.edu.au>
Date:   Mon Oct 10 13:54:15 2016 +1100

    Properly merging branches

commit 2411e6ea8003d268711624da02616e0c893c72dc
Author: TomHamer <tomhamer@live.com>
Date:   Mon Oct 10 11:27:24 2016 +1100

    Intellegent player has begun training, all AI works - optimisations still need to be made to the hard AI

commit 3993f8b3ada0f199d3c2871360d2096bd9bcedaa
Author: TomHamer <tomhamer@live.com>
Date:   Mon Oct 10 11:19:15 2016 +1100

    Intellegent player has begun training, all AI works - optimisations still need to be made to the hard AI

commit 4fccd4985ba13653c5bfa93c653b6e422b3688dd
Author: TomHamer <tomhamer@live.com>
Date:   Sun Oct 9 20:03:25 2016 +1100

    Reset was successful

commit 152ea8fe544bd0d4f6bb86af3766f397939a0725
Merge: f6c68ad 9539d9c
Author: TomHamer <tomhamer@live.com>
Date:   Sun Oct 9 19:57:37 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit f6c68ade26f32bdff923e202b2be75d86303db61
Author: TomHamer <tomhamer@live.com>
Date:   Sun Oct 9 19:57:27 2016 +1100

    Made changes to AI

commit 9e1890fd7d1ceaad9bc366b28a92594204804a6f
Merge: 9a71c37 9539d9c
Author: calum <u6044174@anu.edu.au>
Date:   Sun Oct 9 19:41:13 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/Minimax.java
    #       src/comp1110/ass2/Trainer.java
    #       src/comp1110/ass2/gui/Board.java

commit 9a71c376ddf404a01c290b95ead9967ea11bdc2d
Author: calum <u6044174@anu.edu.au>
Date:   Sun Oct 9 19:36:36 2016 +1100

    Updated UI

commit 9539d9cbdcb95dd83970d1dd22aa7cad2a93901a
Merge: 5706729 7488add
Author: TomHamer <tomhamer@live.com>
Date:   Sun Oct 9 19:34:29 2016 +1100

    Merge remote-tracking branch 'origin/master'

commit 570672911b8fb1699bfd0ebc6e622f44cb6e4e8e
Author: TomHamer <tomhamer@live.com>
Date:   Sun Oct 9 19:34:21 2016 +1100

    Made changes to AI

commit 7488add335071d26851f2f1786da9099e0dd1169
Merge: 9f2bfd8 a83945b
Author: TomHamer <tomhamer@live.com>
Date:   Sun Oct 9 19:21:34 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/BoardState.java
    #       src/comp1110/ass2/gui/Board.java

commit 9f2bfd89a112d6c750161382b3f4373b8f4f51e9
Author: TomHamer <tomhamer@live.com>
Date:   Sun Oct 9 19:14:08 2016 +1100

    Made changes to AI

commit a83945b7ace768e70a45409275d8410bcae32b81
Merge: 4eeac11 eb71aeb
Author: u5932987 <u5932987@anu.edu.au>
Date:   Sat Oct 8 04:41:07 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit 4eeac11f7e518f84c4621c39a0dc501845ed4ada
Merge: 884c955 83100f4
Author: u5932987 <u5932987@anu.edu.au>
Date:   Thu Oct 6 01:44:10 2016 +1100

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/u6044174/comp1140-ass2-thu11t
    
    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit eb71aebf001c0fa133d26818eed4b25c02d95495
Author: TomHamer <tomhamer@live.com>
Date:   Wed Oct 5 16:43:22 2016 +1100

    Easy and medium players complete - hard player complete but slow and not probabalistic due to problems with minimising the size of the search tree

commit 0cad52a8b451cd9bdd15f5e81dc61c3bd2bc27d3
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 4 19:43:25 2016 +1100

    Hint functionality has been implemented

commit 83100f4d6e2a5cb71a4a9921960b7a3415309faa
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 4 15:00:34 2016 +1100

    Music works

commit 4b61e29fe3f2d94e5e0b9d3e1eae1dbdee60cefc
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 4 14:45:15 2016 +1100

    Tweaked evaluation function

commit 51fce58f6a722c7ba4bf28aa059ce3fdf37a19b6
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 4 13:48:22 2016 +1100

    Cleaned and optimised some code

commit 884c9551bd969a6b741d8314090e5d474eccdd91
Merge: 9dc8f00 bc14c12
Author: u5932987 <u5932987@anu.edu.au>
Date:   Tue Oct 4 03:16:47 2016 +1100

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/gui/Board.java

commit 9dc8f00826492939e23246501f293518f62aa806
Author: u5932987 <u5932987@anu.edu.au>
Date:   Tue Oct 4 03:14:45 2016 +1100

    Savegame part 50%completed

commit bc14c12f68e0c2587de7d2a3fe4d1bd38f4763bf
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 4 02:19:52 2016 +1100

    board update - did not erase some error checking code

commit e649d587707d3e8a3bec40000b8158adcd6a76e4
Author: TomHamer <tomhamer@live.com>
Date:   Tue Oct 4 02:18:54 2016 +1100

    Much of the code in board has been cleaned up and is now more workable
    
    An easy bot has been implemented fully with recursion depth of 1
    
    The medium bot is not performing correctly (being beaten by the easy bot)
    
    The impossible bot is still buggy
    
    Began to add hint functionality, which calls an exception as it has a bug.

commit 8bce60096fb1a0523e8727b68f15da0098d318c4
Author: TomHamer <tomhamer@live.com>
Date:   Fri Sep 30 22:51:28 2016 +1000

    Created toDos for Jinyi in board.java, and an enum for different player types

commit 395430afe4626cf19a2b38b257c8236c001c4eac
Author: TomHamer <tomhamer@live.com>
Date:   Fri Sep 30 19:51:36 2016 +1000

    Polished and fixed some bugs in board

commit 0be407a2fd6b3129dfe7d9fc25d71f1d8eff8c54
Merge: 5c8ba36 514b3a7
Author: TomHamer <tomhamer@live.com>
Date:   Fri Sep 30 18:23:25 2016 +1000

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/comp1110/ass2/StratoGame.java
    #       src/comp1110/ass2/gui/Board.java

commit 5c8ba3658823ad6168c369bcdf4591f0d5cb5919
Author: TomHamer <tomhamer@live.com>
Date:   Fri Sep 30 18:21:55 2016 +1000

    Easy and medium AIs now work

commit 514b3a77ccbc431af9f6f9c5a522ffd1b1b057e9
Author: calum <u6044174@anu.edu.au>
Date:   Thu Sep 29 11:14:28 2016 +1000

    Updated comments, authorship details, contribution and originality

commit 33e17e67d2a675b8a714ae2a4ddf83a77e5be438
Author: calum <u6044174@anu.edu.au>
Date:   Wed Sep 28 14:16:06 2016 +1000

    Created game.jar

commit 21ecdcc12b4b382a80cd80b7d3039cfa302dec24
Author: calum <u6044174@anu.edu.au>
Date:   Wed Sep 28 11:46:22 2016 +1000

    Implemnted turns and accounted for the case when the deck runs out

commit 096d2598b449927b4e0537ff4b495d919e20e527
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 27 17:15:45 2016 +1000

    Implemented basic scoring

commit b93abd5d0e452956e7f58edbd0981740400a2837
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 27 10:32:28 2016 +1000

    Made Deck.java an inner class of Board.java, made minor changes to Deck class

commit e0d55d85ff785f7268ce37377edf076e4e7bbae5
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 27 10:00:49 2016 +1000

    Fixed orientation bug in Deck.java

commit 781888ef164bbe287c971a989f62713e2ae7c44d
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 27 09:52:04 2016 +1000

    Optimised graphically adding pieces to the board

commit 61bab4caa05915c7e652aedd0ef65a175f4ac59c
Merge: 38e85a5 bdc2067
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 27 09:08:52 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 38e85a5a3df80349f1915e350bfc9009b900efdc
Merge: 802cca9 3599bbe
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 27 09:05:04 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 802cca9f119e29f272fad20c65bb67275c8b4317
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 27 09:04:53 2016 +1000

    Made a parallelised version of the scoring function, works slower than the original

commit bdc20675f1aecb3665dc13755aa922bb9434bcbe
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Tue Sep 27 06:18:16 2016 +1000

    Increase timeouts by a further 10x; they now have 100x of slack.

commit 050dec19e3fdfa3699e04d1bce7f9e626875a1fb
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Mon Sep 26 09:20:08 2016 +1000

    Add timeouts to tests

commit 3599bbec06a3b813c2e19d91533b5867f8b63ded
Author: TomHamer <tomhamer@live.com>
Date:   Sat Sep 24 00:40:15 2016 +1000

    Origionality statements updated

commit 678c0b5aa1feb0b3cff9e9a9a112ff0550dacb9e
Author: TomHamer <tomhamer@live.com>
Date:   Sat Sep 24 00:39:58 2016 +1000

    Origionality statements updated

commit 863e5859e1e20c3ea1d2995218f7676d849ffcf4
Merge: c3b8d19 27633b9
Author: calum <u6044174@anu.edu.au>
Date:   Fri Sep 23 14:50:17 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 27633b941d5f7ea4d409581c88c7e7a1bc4d1353
Merge: f4f8450 098c02e
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 22 16:58:28 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit f4f84506a3d93e271eb40e48d0bf470648d2ee96
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 22 16:58:08 2016 +1000

    Ensure that valid placements always get tested in their complete form.

commit 79c7a488c1acef454f9ac9d5fbb49c9feb42f323
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 22 16:56:56 2016 +1000

    Added a test to catch placements that go off the board.

commit c3b8d19164841f2b3cffa1abe31bf91eb9778b9c
Author: calum <u6044174@anu.edu.au>
Date:   Thu Sep 22 01:22:54 2016 +1000

    updated originality

commit 44ce94ada960be1ca1dc102edbf7155c721cd772
Author: calum <u6044174@anu.edu.au>
Date:   Thu Sep 22 01:19:13 2016 +1000

    Changed union class to be slightly more correct. Added test file for IsMoveValid.

commit 8b4e7fec85aad8a79046f9ebe740cc474be32361
Merge: fbd9c2c dd69460
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 20 09:51:26 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit fbd9c2c5a457a5277fa0639ca62ef4a81f0098b1
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 20 09:50:32 2016 +1000

    Fixed scoring, now passes all tests

commit 446191498e6588e1d06bfd21f91674e5b569b9ac
Merge: f7fcaf6 098c02e
Author: calum <u6044174@anu.edu.au>
Date:   Tue Sep 20 09:11:27 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 098c02e515da24ce3725b05f4030979d414fff8d
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Fri Sep 16 11:57:55 2016 +1000

    Clarify scoring

commit 769ee8a1bcc7a85a95e749671113fcff8843ae35
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Fri Sep 16 11:44:39 2016 +1000

    Fix bug in scoring.   When there is more than one region of the maximum size, the one with the greatest height should be used to calculate the score.

commit f7fcaf6682ed0d7ada64df99b646833db22477c7
Merge: 18b3eca 7ce9678
Author: calum <u6044174@anu.edu.au>
Date:   Fri Sep 16 10:59:41 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit 7ce967876b5dbb609eff609897f3b022dfb1264e
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Thu Sep 15 21:52:47 2016 +1000

    Added tests for tasks 7 and 10

commit dd69460da198aa929a5d0fa59000802540be1422
Author: TomHamer <tomhamer@live.com>
Date:   Fri Sep 9 19:49:01 2016 +1000

    Added minimax functionality for AI and the abstract type Brain, which minimax will extend. The minimax is untested.

commit 18b3eca1b904b578ce75da53dcaaadb0eea2f09a
Author: calum <u6044174@anu.edu.au>
Date:   Thu Sep 1 11:55:23 2016 +1000

    Updated D-originality, made individual

commit c932ac534c6423c9643d8c37257a36d220a2cb83
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Sep 1 00:35:51 2016 +1000

    Update D-originality-u1234567.md

commit 8093b2ac5a594fc1b707c80a7e56ad27fb13f994
Author: TomHamer <tomhamer@live.com>
Date:   Fri Aug 26 14:27:21 2016 +1000

    Bugs fixed in drag and drop

commit 54532d6ecddab6ef7c98ed367c295afea1270139
Merge: a6d59b5 a2b96d2
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 25 11:51:43 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit a6d59b541c949f6a77afc7e3d424ab81f0ff6d71
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 25 11:51:33 2016 +1000

    Drag and drop now working reletively well, still problems with rotation

commit a2b96d2c18f6349a28b23ad7772844cfe844fc0f
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 25 11:22:39 2016 +1000

    Update details.md

commit fd5c47f1e56daaecd1b47ebc8ec0bccc3521c1f3
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 25 11:16:52 2016 +1000

    Version for

commit c0e2c153d18d8d6a5376a6ef9c4a525d6dfe0c90
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 25 11:16:10 2016 +1000

    Dummy commit

commit 1ee8c07b60fb2748155200be197c52f91893a735
Merge: 6977d4d c6f90a6
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 25 11:14:38 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 6977d4d631eeab75a9409f239a8af6a3804ac4eb
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 25 11:14:27 2016 +1000

    Version for part C

commit c6f90a657df06d9d89c59a907f88f46d1a268bce
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 25 00:02:27 2016 +1000

    Update C-contribution.md

commit e6699fd6ec8631baab20fc8ef7f1d3afee2e4a38
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 25 00:00:47 2016 +1000

    Update C-originality.md

commit b5f2ef85cec6b339f351f7f1bee46a251782d146
Merge: 2e86424 77555fa
Author: TomHamer <tomhamer@live.com>
Date:   Wed Aug 24 12:52:37 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 2e86424cf26c44918a706e0917f8c7798fd37d0f
Author: TomHamer <tomhamer@live.com>
Date:   Wed Aug 24 12:52:27 2016 +1000

    Pieces can be placed on board, but drag and drop needs tweaking, and there are problems with the piece that the user is dragging formatting behind the board.

commit 77555fa1517bf1da7f512c1926dc44d615ea4019
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Tue Aug 23 09:02:42 2016 +1000

    Update C-originality.md

commit cebcd7c979a15ce28103198c2935557e2094414c
Merge: 6c21ad6 3a480a3
Author: TomHamer <tomhamer@live.com>
Date:   Mon Aug 22 21:21:24 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 6c21ad68b753e85d41b9f2bf359ebece9cd89209
Author: TomHamer <tomhamer@live.com>
Date:   Mon Aug 22 21:21:17 2016 +1000

    Began to implement piece placement

commit 3a480a39fd489337ca324b5a74ad44dc5b1aac1a
Author: calum <u6044174@anu.edu.au>
Date:   Mon Aug 22 19:15:02 2016 +1000

    Added comments to StratoGame.java, Tile.java and BoardState.java

commit 1c9c1b3fa7e1ec176752da4ab5fb3d050ae4b7ba
Author: calum <u6044174@anu.edu.au>
Date:   Mon Aug 22 18:39:48 2016 +1000

    Updated tasks 3, 4, 6 to properly pass tests. They have now been formally tested and are fully functional.

commit 7733bb561cb58a4ba6aeed48f3eb47f39c56a0e4
Merge: e880c5f e81747c
Author: calum <u6044174@anu.edu.au>
Date:   Mon Aug 22 18:38:31 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit e81747c45d70e73a483472df2fa68dc262411ecd
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Mon Aug 22 12:26:29 2016 +1000

    Fixed tests to ensure that stacked placements straddle at least two tiles below, in accordance with the rules.

commit e880c5fa0c0ee693f92c131dae2cfb503d9aafc4
Merge: 3e50085 a44528d
Author: calum <u6044174@anu.edu.au>
Date:   Mon Aug 22 10:43:00 2016 +1000

    Merge branch 'master' of https://gitlab.cecs.anu.edu.au/comp1110/comp1140-ass2

commit a44528db6571072b8e637ac9af7f9e492f80e0d1
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Mon Aug 22 07:50:38 2016 +1000

    Added tests for tasks 3, 4, and 6.

commit 3e500857ce038d36620377a4f46983c8dafe72a9
Merge: 0376754 446ba56
Author: TomHamer <tomhamer@live.com>
Date:   Sun Aug 21 14:46:32 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 0376754280ae29295a160888ec7ff3217e25efa3
Author: TomHamer <tomhamer@live.com>
Date:   Sun Aug 21 14:46:18 2016 +1000

    Merged successfully onto main

commit 446ba5698995b274e745075d551ccb94e32031eb
Author: TomHamer <tomhamer@live.com>
Date:   Sun Aug 21 02:59:26 2016 +1000

    Experiment was successful - code needs to be cleaned up and made more efficient.

commit e24dc89cec2d86c7753ef7b9403f394f4d737090
Merge: 8cfff2b 156c489
Author: TomHamer <tomhamer@live.com>
Date:   Sun Aug 21 02:25:51 2016 +1000

    Merge remote-tracking branch 'remotes/origin/BobsExperiment' into Dndexperiement

commit 8cfff2b532c77c3d266f706945efae8558895f42
Author: TomHamer <tomhamer@live.com>
Date:   Sun Aug 21 02:23:06 2016 +1000

    Experiment was successful - code needs to be cleaned up and made more efficient. Clicking and dragging from the deck will print out the coordinates of where you tried to place the piece, the piece's orientation (which can be changed by scrolling) and the name of the piece. The program generates a random deck and deals out pieces one after the other.

commit 7180a2a9067db440f5d321c32526f00e45f0b72b
Author: TomHamer <tomhamer@live.com>
Date:   Sun Aug 21 01:40:57 2016 +1000

    Drag and drop experiment - made some improvements to the implementation. There are no errors. Image can be dragged and snaps back.

commit d6c5bab9d37ef0b80eeedb552a8bb632b3e8073a
Author: TomHamer <tomhamer@live.com>
Date:   Sat Aug 20 22:27:57 2016 +1000

    Drag and drop experiment - made some improvements to the implementation. There are no errors.

commit 6b005ddeff3b71b08e3a99ee4af5b2731dc26f00
Author: TomHamer <tomhamer@live.com>
Date:   Sat Aug 20 20:47:15 2016 +1000

    Drag and drop experiment

commit 8b90046b98e167a2fc997eb7c105b549c940374f
Author: calum <u6044174@anu.edu.au>
Date:   Thu Aug 18 19:09:11 2016 +1000

    Made some optimisations to scoring method.

commit 4b6865c9db9097c94e6f34195f0ffd47362229ac
Author: calum <u6044174@anu.edu.au>
Date:   Thu Aug 18 17:48:15 2016 +1000

    Score method implemented in StratoGame.java. Has worked in the few cases I've tested, function awaits formal testing however.

commit 5e7f5014507d1588e9023fe65298a8cacb348854
Merge: 0a8240a d81feec
Author: Calum Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 18 12:22:10 2016 +1000

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/gittest/Main.java

commit d81feec910f4f73708902ec3524b41d4a8f61819
Merge: cd64ad2 389f1bb
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:21:25 2016 +1000

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       src/gittest/Main.java

commit cd64ad2943b684e873f1d662ceb3e0f20ce7e863
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:20:05 2016 +1000

    main changed (bob)

commit 0a8240a3368b45e0ad2368d8c3c69c54a0ad0abc
Author: Calum Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 18 12:19:09 2016 +1000

    Cindy commits C

commit 389f1bb74840ef581b7ad24d7eb4f0699f63bc9d
Author: calum <u6044174@anu.edu.au>
Date:   Thu Aug 18 12:19:07 2016 +1000

    Calum commits A

commit 156c489444c6c9fd075af6e5c492b04380f82f04
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:16:17 2016 +1000

    bob changed b again

commit 68d02f40f5fbf896d76e8dcfdeaa38fbfe6d7fa3
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:15:28 2016 +1000

    bob changed b

commit 8af96d32d98640cb6191594b1b67d95ccf2a995f
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:10:48 2016 +1000

    Experiment 2

commit 44608bafaa460ed2f6129b05c4e4262b62dcdba3
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:10:24 2016 +1000

    experiment 1

commit 7c1b54e31bdb667eb943cbea164e8bb22fdb94dc
Merge: f56698e d97e2cd
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:08:00 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit d97e2cd8cef321ccf1014cf9b2929665926207eb
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 18 12:07:05 2016 +1000

    task #4

commit f56698e45484f2d77181c48267058471f1ab82d4
Author: TomHamer <tomhamer@live.com>
Date:   Thu Aug 18 12:05:55 2016 +1000

    task 3

commit 528db991198de0acaec03030789f51fd0fa81d84
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 18 12:02:31 2016 +1000

    Task #2

commit 6fb0100e8c434b68c778b7ab6c10b055ecec7305
Author: calum <u6044174@anu.edu.au>
Date:   Thu Aug 18 11:29:28 2016 +1000

    Updated UML (again?)

commit fbdc9ad1c78093eb879da8e8dcaabd2a1242f9c3
Author: calum <u6044174@anu.edu.au>
Date:   Thu Aug 18 11:27:01 2016 +1000

    Updated UML

commit be34473b84862203302de9cccbfb92429cc71756
Merge: 53c918a ed4a454
Author: calum <u6044174@anu.edu.au>
Date:   Thu Aug 18 11:20:29 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit 53c918ac2580d086707fa8bc6f462434dd98003a
Author: calum <u6044174@anu.edu.au>
Date:   Thu Aug 18 11:20:14 2016 +1000

    UML diagram added

commit ed4a454aefefa5a7750d82b515a718b109b06838
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 18 11:11:08 2016 +1000

    Update B-contribution.md

commit b21c22c53ed5cab06f55e1b796ae27e0dafb99ee
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Thu Aug 18 11:09:54 2016 +1000

    Update B-originality.md

commit 12de91577f5eacced4d8f0134bb766fc573eb146
Author: calum <u6044174@anu.edu.au>
Date:   Wed Aug 17 18:13:59 2016 +1000

    Viewer finished. Implemented new methods in Tile.java and BoardState.java to interpret the board as a JavaFX group which can be displayed.

commit 8c9be7831dc1cbadaa518484e67bedb3c9109ce5
Author: calum <u6044174@anu.edu.au>
Date:   Tue Aug 16 16:55:26 2016 +1000

    Fixed bugs in BoardState and Pieces and solved compatibility issues, the classes now function together properly.

commit f30c670ed08e4f5b38a874bc4276d5ea191d0cea
Author: TomHamer <tomhamer@live.com>
Date:   Tue Aug 16 15:49:57 2016 +1000

    fixed a bug in PiecesTest class - and both tests still pass.

commit 3ed588dddea380d78a6e858e21f73377b0064866
Author: TomHamer <tomhamer@live.com>
Date:   Tue Aug 16 15:24:09 2016 +1000

    Updated the log, and altered the pieces.java class to account for the special starting piece 'U'.

commit c88b380ec2343a9cd358efd749c2b79ffd65e1d0
Merge: e1a7f62 a86977d
Author: TomHamer <tomhamer@live.com>
Date:   Tue Aug 16 13:28:19 2016 +1000

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #       tests/comp1110/ass2/PiecesTest.java

commit e1a7f6263c8b396744d6a0fd6bf79ca97057b2da
Author: TomHamer <tomhamer@live.com>
Date:   Tue Aug 16 13:27:45 2016 +1000

    Test has been enhanced for pieces class. Pieces class has been written such that it decodes the string, creating colours and coordinates, and passes the test.

commit a86977d29757a01f1490848a4e24fa0fcfceef5f
Author: calum <u6044174@anu.edu.au>
Date:   Tue Aug 16 10:47:55 2016 +1000

    Fixed an issue with isPlacementWellFormed, still untested

commit dd7a0b936d9d3b7965920c36a95a7730812f5a51
Author: calum <u6044174@anu.edu.au>
Date:   Tue Aug 16 09:53:37 2016 +1000

    Code for tasks 3, 4, 6 implemented. Not yet tested.

commit 714156943c3f26c030a2e9dc206f639c83d5ab23
Author: calum <u6044174@anu.edu.au>
Date:   Tue Aug 16 09:32:26 2016 +1000

    Filled out the methods of the BoardState

commit c12527febbe9822ddb7d06af6256c33e6a42001f
Author: TomHamer <tomhamer@live.com>
Date:   Tue Aug 16 00:27:36 2016 +1000

    A test file has been created and two test cases have been written for the "Pieces" class.

commit 7f66054c8b955416d5bb2f949a327284657ba84d
Merge: e9e0ca5 bb01bb5
Author: calum <u6044174@anu.edu.au>
Date:   Mon Aug 15 20:54:46 2016 +1000

    Merge remote-tracking branch 'origin/master'

commit e9e0ca504036ced3e27b2f2dd87058340982fe32
Author: calum <u6044174@anu.edu.au>
Date:   Mon Aug 15 20:54:26 2016 +1000

    Created framework for classes to be used in the project, including a class to represent a single tile on the board, a class to represent the entire board and a class to abstract strings representing piece placements. Code has yet to be filled in.

commit bb01bb52d8d562561eab154d46f5c56c4ed313dc
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Sat Aug 13 16:55:19 2016 +1000

    Update details.md

commit 045cccb29c7745579e233e6188f6e061722c6244
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Sat Aug 13 16:55:01 2016 +1000

    Update details.md

commit a0a6923250903ae8af28b2dddbf3c328ec16511b
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Sat Aug 13 16:54:28 2016 +1000

    Update details.md

commit 7525b16ed8c6adffffca120fc2b71fe1238f35dc
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Sat Aug 13 16:53:59 2016 +1000

    Update details.md

commit f00937820045e75a2defa4d7bff25fdb04b1ae0d
Author: Calum  Snowdon <u6044174@anu.edu.au>
Date:   Sat Aug 13 16:53:16 2016 +1000

    Added details.md, outlining roles and contact details.

commit dcc4ca54fb01879ea421ac0f7f55951609504cc3
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Wed Aug 10 06:55:32 2016 +1000

    Update README.md

commit 88186a253ff9452256a764b0e1d4698817611c4e
Author: COMP1110 Sem 2 2016 <steve.blackburn+comp1110-2016@anu.edu.au>
Date:   Tue Aug 9 12:47:52 2016 +1000

    Initial import
```
## Changes
```
Only in comp1140-ass2/.idea: artifacts
diff -ru -x .git ../master/comp1140-ass2/.idea/comp1140-ass2.iml comp1140-ass2/.idea/comp1140-ass2.iml
--- ../master/comp1140-ass2/.idea/comp1140-ass2.iml	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/.idea/comp1140-ass2.iml	2016-10-15 14:05:54.000000000 +1100
@@ -9,5 +9,25 @@
     </content>
     <orderEntry type="inheritedJdk" />
     <orderEntry type="sourceFolder" forTests="false" />
+    <orderEntry type="module-library" scope="TEST">
+      <library name="JUnit4">
+        <CLASSES>
+          <root url="jar://$APPLICATION_HOME_DIR$/lib/junit-4.12.jar!/" />
+          <root url="jar://$APPLICATION_HOME_DIR$/lib/hamcrest-core-1.3.jar!/" />
+        </CLASSES>
+        <JAVADOC />
+        <SOURCES />
+      </library>
+    </orderEntry>
+    <orderEntry type="module-library">
+      <library>
+        <CLASSES>
+          <root url="jar://$USER_HOME$/Downloads/jcommon-1.0.23/jcommon-1.0.23.jar!/" />
+        </CLASSES>
+        <JAVADOC />
+        <SOURCES />
+      </library>
+    </orderEntry>
+    <orderEntry type="library" name="la4j-0.4.0-sources.jar" level="project" />
   </component>
 </module>
\ No newline at end of file
Only in comp1140-ass2/.idea: libraries
diff -ru -x .git ../master/comp1140-ass2/.idea/misc.xml comp1140-ass2/.idea/misc.xml
--- ../master/comp1140-ass2/.idea/misc.xml	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/.idea/misc.xml	2016-10-15 14:05:54.000000000 +1100
@@ -3,13 +3,6 @@
   <component name="EntryPointsManager">
     <entry_points version="2.0" />
   </component>
-  <component name="MavenImportPreferences">
-    <option name="generalSettings">
-      <MavenGeneralSettings>
-        <option name="mavenHome" value="Bundled (Maven 3)" />
-      </MavenGeneralSettings>
-    </option>
-  </component>
   <component name="ProjectLevelVcsManager" settingsEditedManually="false">
     <OptionsSetting value="true" id="Add" />
     <OptionsSetting value="true" id="Remove" />
diff -ru -x .git ../master/comp1140-ass2/.idea/modules.xml comp1140-ass2/.idea/modules.xml
--- ../master/comp1140-ass2/.idea/modules.xml	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/.idea/modules.xml	2016-10-15 14:05:54.000000000 +1100
@@ -5,4 +5,4 @@
       <module fileurl="file://$PROJECT_DIR$/.idea/comp1140-ass2.iml" filepath="$PROJECT_DIR$/.idea/comp1140-ass2.iml" />
     </modules>
   </component>
-</project>
+</project>
\ No newline at end of file
Only in comp1140-ass2: META-INF
Only in comp1140-ass2: Report
diff -ru -x .git ../master/comp1140-ass2/admin/B-contribution.md comp1140-ass2/admin/B-contribution.md
--- ../master/comp1140-ass2/admin/B-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/B-contribution.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,7 +1,7 @@
 We declare that the work toward our submission of Stage B was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0234567 30
+* u6044174 50
+* u6081614 50
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0234567)
+
+Signed: Calum Snowdon (u6044174), Tom Hamer (u6081614)
diff -ru -x .git ../master/comp1140-ass2/admin/B-originality.md comp1140-ass2/admin/B-originality.md
--- ../master/comp1140-ass2/admin/B-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/B-originality.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,9 +1,5 @@
 We declare that the work we have submitted for Stage B of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* We figured out how to do the UML diagram based on the lecture material
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-*  ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Calum Snowdon (u6044174), Tom Hamer (u6081614)
diff -ru -x .git ../master/comp1140-ass2/admin/C-contribution.md comp1140-ass2/admin/C-contribution.md
--- ../master/comp1140-ass2/admin/C-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/C-contribution.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,7 +1,6 @@
 We declare that the work toward our submission of Stage C was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0234567 30
+* u6044174 60
+* u6081614 40
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0234567)
+Signed: Calum Snowdon (u6044174), Tom Hamer (u6081614)
diff -ru -x .git ../master/comp1140-ass2/admin/C-originality.md comp1140-ass2/admin/C-originality.md
--- ../master/comp1140-ass2/admin/C-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/C-originality.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,9 +1,6 @@
 We declare that the work we have submitted for Stage C of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* The method of using boxes which extend ImageView to display images was inspired by code from Board.java in Assignment 1. The square class we used was essentially
+  copy/pasted from assignment 1.
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-*  ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Calum Snowdon (u6044174), Tom Hamer (u6081614)
Only in ../master/comp1140-ass2/admin: D-originality-u1234567.md
Only in comp1140-ass2/admin: D-originality-u6044174.md
Only in ../master/comp1140-ass2/admin: E-originality-u1234567.md
Only in comp1140-ass2/admin: E-originality-u6044174.md
diff -ru -x .git ../master/comp1140-ass2/admin/F-contribution.md comp1140-ass2/admin/F-contribution.md
--- ../master/comp1140-ass2/admin/F-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/F-contribution.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,8 +1,7 @@
 We declare that the work toward our submission of Stage F was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0123456 30
+* u6044174 50
+* u6081614 50
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Calum Snowdon (u6044174), Tom Hamer (u6081614)
 
diff -ru -x .git ../master/comp1140-ass2/admin/F-originality.md comp1140-ass2/admin/F-originality.md
--- ../master/comp1140-ass2/admin/F-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/F-originality.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,9 +1,5 @@
 We declare that the work we have submitted for Stage F of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* The JavaFX code for draggable pieces was inspired by the similar code from assignment 1
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
-
-* ....
-
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+Signed: Calum Snowdon (u6044174), Tom Hamer (u6081614)
Only in ../master/comp1140-ass2/admin: G-best-u1234567.md
Only in comp1140-ass2/admin: G-best-u6044174.md
Only in comp1140-ass2/admin: G-best-u6081614.md
diff -ru -x .git ../master/comp1140-ass2/admin/G-contribution.md comp1140-ass2/admin/G-contribution.md
--- ../master/comp1140-ass2/admin/G-contribution.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/G-contribution.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,8 +1,9 @@
 We declare that the work toward our submission of Stage G was distributed among the group members as follows:
 
-* u2345678 30
-* u3456789 40
-* u0123456 30
+U6081814 47.5
+U6044174 47.5
+Jingyi Lu  5
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+
+Signed: Calum Snowdon (u2345678), Tom Hamer (u6081614), and Jingyi Lu
 
diff -ru -x .git ../master/comp1140-ass2/admin/G-features.md comp1140-ass2/admin/G-features.md
--- ../master/comp1140-ass2/admin/G-features.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/G-features.md	2016-10-15 14:05:54.000000000 +1100
@@ -12,3 +12,14 @@
  - Advanced computer opponent
 
 additional features...
+
+- (bad) concurrent scoring algorithm
+- (bad) neural network trained opponenet
+- (bad) monte carlo tree search player
+- (bad) music (press m)
+- advanced computer opponents easy, medium and hard
+- animated AI piece placement
+- pretty UI
+
+
+
diff -ru -x .git ../master/comp1140-ass2/admin/G-originality.md comp1140-ass2/admin/G-originality.md
--- ../master/comp1140-ass2/admin/G-originality.md	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/admin/G-originality.md	2016-10-15 14:05:54.000000000 +1100
@@ -1,9 +1,19 @@
 We declare that the work we have submitted for Stage G of this assignment and all stages before it is entirely our own work, with the following documented exceptions:
 
-* The idea of using <...> to make the game run faster came from a discussion with <...> (noted in source code comments)
+* The technique to implement radio buttons in Board.java was gathered from http://docs.oracle.com/javafx/2/ui_controls/radio-button.htm
 
-* The code in class <...> is based on a solution we found when researching the problem (URL provided in source code comments)
+* http://docs.oracle.com/javafx/2/animations/basics.htm was referenced to figure out how to do animations for Board.java
 
-* ....
+* http://stackoverflow.com/questions/26554814/javafx-updating-gui was referenced to figure out how to update UI while performing computations
 
-Signed: Mary Jones (u2345678), Bob Smith (u3456789), and Sue Black (u0123456)
+* http://la4j.org (linear algebra package for neural network)
+
+* based monte carlo on https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/ for guidence on construction and theory for this algorithm
+
+* Assignment one music and drag and drop code
+
+* http://stackoverflow.com/questions/27527090/finding-the-best-move-using-minmax-with-alpha-beta-pruning for inspiration with java alpha beta pruning
+
+* The Elements of Statistical Learning (Trevor Hastie) (back and forward propagation equations)
+
+Signed: Calum Snowdon (u6044174)
Binary files ../master/comp1140-ass2/assets/H.png and comp1140-ass2/assets/H.png differ
Only in comp1140-ass2: details.md
Only in comp1140-ass2: feedback.md
Only in comp1140-ass2: game.jar
Only in comp1140-ass2: presentation.pdf
Only in comp1140-ass2/src/comp1110/ass2: AI
Only in comp1140-ass2/src/comp1110/ass2: BoardState.java
Only in comp1140-ass2/src/comp1110/ass2: Colour.java
Only in comp1140-ass2/src/comp1110/ass2: Pieces.java
Only in comp1140-ass2/src/comp1110/ass2: PlayGames.java
diff -ru -x .git ../master/comp1140-ass2/src/comp1110/ass2/StratoGame.java comp1140-ass2/src/comp1110/ass2/StratoGame.java
--- ../master/comp1140-ass2/src/comp1110/ass2/StratoGame.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/src/comp1110/ass2/StratoGame.java	2016-10-15 14:05:54.000000000 +1100
@@ -1,13 +1,16 @@
 package comp1110.ass2;
 
+import java.util.*;
+
+
 /**
  * This class provides the text interface for the Strato Game
  *
  * The game is based directly on Gigamic's Stratopolis game
  * (http://boardgamegeek.com/boardgame/125022/stratopolis)
  */
+// Written jointly by Calum Snowdon and Tom Hamer
 public class StratoGame {
-
     /**
      * Determine whether a tile placement is well-formed according to the following:
      * - it consists of exactly four characters
@@ -19,9 +22,17 @@
      * @param tilePlacement A string describing a tile placement
      * @return True if the tile placement is well-formed
      */
-    static boolean isTilePlacementWellFormed(String tilePlacement) {
+    public static boolean isTilePlacementWellFormed(String tilePlacement) {
         // FIXME Task 3: determine whether a tile placement is well-formed
-        return false;
+        char x = tilePlacement.charAt(0);
+        char y = tilePlacement.charAt(1);
+        char p = tilePlacement.charAt(2);
+        char o = tilePlacement.charAt(3);
+
+        return (x >= 'A' && x <= 'Z' &&
+                y >= 'A' && y <= 'Z' &&
+                p >= 'A' && p <= 'U' &&
+                o >= 'A' && o <= 'D');
     }
 
     /**
@@ -36,22 +47,57 @@
      * @param placement A string describing a placement of one or more tiles
      * @return True if the placement is well-formed
      */
-    static boolean isPlacementWellFormed(String placement) {
+    public static boolean isPlacementWellFormed(String placement) {
         // FIXME Task 4: determine whether a placement is well-formed
-        String foo;
-        return false;
+        if (placement == null) {return false;}
+        if (placement.isEmpty()) {return false;}
+        if (!placement.substring(0, 4).equals("MMUA")) {return false;}
+        Set<Character> chars = new HashSet<>();
+        Set<Character> chars2 = new HashSet<>();
+
+        boolean green = true;
+
+        for (int i = 4; i <= placement.length() - 4; i += 4) {
+            if (!isTilePlacementWellFormed(placement.substring(i, i+4))) {return false;}
+            if (green && (placement.charAt(i+2) <= 'J')) {return false;}
+            if (!green && !(placement.charAt(i+2) <= 'J')) {return false;}
+            if (chars.contains(placement.charAt(i+2))) {
+                if (chars2.contains(placement.charAt(i+2))) {
+                    return false;
+                }
+                chars2.add(placement.charAt(i+2));
+            }
+            green = !green;
+            chars.add(placement.charAt(i+2));
+        }
+        return (placement.length() % 4 == 0);
     }
 
     /**
      * Determine whether a placement is valid.  To be valid, the placement must be well-formed
      * and each tile placement must follow the game's placement rules.
-     *
+
      * @param placement A placement string
      * @return True if the placement is valid
      */
-    static boolean isPlacementValid(String placement) {
+    public static boolean isPlacementValid(String placement) {
         // FIXME Task 6: determine whether a placement is valid
-        return false;
+        if (placement == null) {return false;}
+        if (placement.isEmpty()) {return false;}
+
+        BoardState board = new BoardState();
+
+        if (!placement.substring(0, 4).equals("MMUA")) {return false;}
+        else {board.PlaceTile("MMUA");}
+
+        for (int i = 4; i <= placement.length() - 4; i += 4) {
+            if (board.IsValidMove(placement.substring(i, i+4))) {
+                board.PlaceTile(placement.substring(i, i+4));
+            } else {
+                return false;
+            }
+        }
+        return isPlacementWellFormed(placement);
     }
 
     /**
@@ -65,20 +111,34 @@
      */
     static int getScoreForPlacement(String placement, boolean green) {
         // FIXME Task 7: determine the score for a player given a placement
-        return 0;
+        return (new BoardState(placement)).BoardScore(green);
     }
 
     /**
      * Generate a valid move that follows from: the given placement, a piece to
      * play, and the piece the opponent will play next.
      *
-     * @param placement  A valid placement string indicating a game state
-     * @param piece  The piece you are to play ('A' to 'T')
-     * @param opponentsPiece The piece your opponent will be asked to play next ('A' to 'T' or 0 if last move).
+     //* @param placement  A valid placement string indicating a game state
+     //* @param piece  The piece you are to play ('A' to 'T')
+     //* @param opponentsPiece The piece your opponent will be asked to play next ('A' to 'T' or 0 if last move).
      * @return A string indicating a valid tile placement that represents your move.
      */
-    static String generateMove(String placement, char piece, char opponentsPiece) {
-        // FIXME Task 10: generate a valid move
-        return null;
+
+    //generates a random move by generating all the moves and selecting a random one
+    public static String generateMove(String placement, char piece, char opponentsPiece) {
+        char[] redDeck = new char[] {'A','B','C','D','E','F','G','H','I','J'};
+        boolean playerIsRed = false;
+        for(char i:redDeck) {
+            if(piece == i) {
+                playerIsRed = true; // find if the player is red or green
+                break;
+            }
+        }
+        Random rand = new Random();
+        ArrayList<String> mList = (new BoardState(placement)).generateAllPossibleMoves(playerIsRed,piece);
+        int r = rand.nextInt(mList.size());
+        return mList.get(r); // return the first randomly generated move
+
     }
+
 }
Only in comp1140-ass2/src/comp1110/ass2: Tile.java
diff -ru -x .git ../master/comp1140-ass2/src/comp1110/ass2/gui/Board.java comp1140-ass2/src/comp1110/ass2/gui/Board.java
--- ../master/comp1140-ass2/src/comp1110/ass2/gui/Board.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/src/comp1110/ass2/gui/Board.java	2016-10-15 14:05:54.000000000 +1100
@@ -1,22 +1,930 @@
 package comp1110.ass2.gui;
 
+import comp1110.ass2.AI.*;
+import comp1110.ass2.AI.NeuralNetworkFiles.IntelligentPlayer;
+import comp1110.ass2.AI.NeuralNetworkFiles.NN1HL;
+import comp1110.ass2.BoardState;
+import comp1110.ass2.Colour;
+import comp1110.ass2.Pieces;
+import comp1110.ass2.Tile;
+import javafx.animation.ParallelTransition;
+import javafx.animation.RotateTransition;
+import javafx.animation.TranslateTransition;
 import javafx.application.Application;
+import javafx.application.Platform;
+import javafx.beans.property.BooleanProperty;
+import javafx.beans.property.SimpleBooleanProperty;
+import javafx.concurrent.Task;
+import javafx.scene.Group;
+import javafx.scene.Scene;
+import javafx.scene.control.*;
+import javafx.scene.input.KeyCode;
+import javafx.scene.media.AudioClip;
+import javafx.scene.paint.Color;
+import javafx.scene.shape.Rectangle;
+import javafx.scene.text.Font;
+import javafx.scene.text.Text;
 import javafx.stage.Stage;
+import javafx.scene.image.Image;
+import javafx.scene.image.ImageView;
+import javafx.util.Duration;
+
+import java.io.*;
+import javafx.animation.FadeTransition;
+import java.util.ArrayList;
+import java.util.Random;
+import java.util.concurrent.FutureTask;
+
+import static comp1110.ass2.gui.Board.PlayerMode.Human;
+import static comp1110.ass2.gui.Board.PlayerMode.Easy;
+import static comp1110.ass2.gui.Board.PlayerMode.Medium;
+import static comp1110.ass2.gui.Board.PlayerMode.Hard;
+
+/*
+    Developed jointly by Tom Hamer and Calum Snowdon.
+    Calum created the UI and handled the logical flow of the game (making it work)
+    Tom created the AI and the draggable icons
+ */
 
 public class Board extends Application {
+
     private static final int BOARD_WIDTH = 933;
     private static final int BOARD_HEIGHT = 700;
+    private static final int SQUARE_SIZE = 23;
+    private static final int DECK_COORD_G_X = 55;
+    private static final int DECK_COORD_G_Y = 50;
+    private static final int DECK_COORD_R_X = BOARD_WIDTH - 101;
+    private static final int DECK_COORD_R_Y = 50;
+    private BoardState boardState = new BoardState("MMUA");
+    private Group root = new Group();
+    private Group displayBoard; // Graphical representation of the board
+    private Group titleScreen = new Group(), overScreen = new Group(); // Title screen visible upon opening the game
+    private Text overText = new Text(), overBody = new Text(); // Title revealed on game over
+    private Text greenScore, redScore; // Text objects to display each player's score
+    private boolean greensTurn = true; // Used to keep track of whose turn it is
+    private boolean soundOn = false; // Used to keep track of whether or not music is playing
+    private Deck RDeck, GDeck; // Used to represent each player's deck
+    private Text remainingG, remainingR; // Text showing how many pieces each player has left
+    private Group hint = null;
+    private ToggleGroup greenOptions = new ToggleGroup(); // Contains radio buttons to represent settings
+    private ToggleGroup redOptions   = new ToggleGroup();
+    private int boardIndex; // Used to keep track of position of displayBoard within root
+    private Player greenAI, redAI; // Used to make moves for AI players when necessary
+
+    private BooleanProperty gameStarted = new SimpleBooleanProperty(false); // Used to toggle access to settings for when game is not running
+
+    private PlayerMode redMode = Human, greenMode = Human; // Used to keep track of what type of Player each colour is
+
+    enum PlayerMode {
+        Human, Easy, Medium, Hard // Used to allow for different types of AIs and players
+    }
+
+    public void start(Stage primaryStage) {
+
+        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, Color.BLACK);
+
+        primaryStage.setTitle("Stratopolis");
+        primaryStage.setX(250);
+        primaryStage.setY(100);
+
+        primaryStage.sizeToScene();
+        primaryStage.setScene(scene);
+        primaryStage.show();
+
+        Platform.setImplicitExit(true);
+
+        // ----- Large section initializing UI ----- //
+
+        // Creates the rectangles in the background
+        for (double x = -100; x < BOARD_WIDTH; x += 300) {
+            for (double y = 0; y < BOARD_HEIGHT; y += 140) {
+                Rectangle toAdd = new Rectangle(x - 150 * (((int) (y / 140)) % 2) + 3, y + 3, 294, 134);
+                toAdd.setFill(Color.DIMGRAY);
+                root.getChildren().add(toAdd);
+            }
+        }
+
+        greenScore = new Text();
+        redScore = new Text();
+
+        Rectangle greenBox = new Rectangle(DECK_COORD_G_X - SQUARE_SIZE * 1.5, DECK_COORD_G_Y - SQUARE_SIZE, SQUARE_SIZE * 5, 220);
+        greenBox.setArcHeight(15);
+        greenBox.setArcWidth(15);
+        greenBox.setStroke(Color.BLACK);
+        greenBox.setFill(Color.DARKGREEN);
+        greenBox.setStrokeWidth(3);
+        root.getChildren().add(greenBox);
+
+        Rectangle redBox = new Rectangle(DECK_COORD_R_X - SQUARE_SIZE * 1.5, DECK_COORD_R_Y - SQUARE_SIZE, SQUARE_SIZE * 5, 220);
+        redBox.setArcHeight(15);
+        redBox.setArcWidth(15);
+        redBox.setStroke(Color.BLACK);
+        redBox.setFill(Color.DARKRED);
+        redBox.setStrokeWidth(3);
+        root.getChildren().add(redBox);
+
+        root.getChildren().add(greenScore);
+        root.getChildren().add(redScore);
+
+        greenScore.setFont(new Font(18));
+        redScore.setFont(new Font(18));
+
+        greenScore.relocate(DECK_COORD_G_X - SQUARE_SIZE / 2, DECK_COORD_G_Y + SQUARE_SIZE * 2.5);
+        redScore.relocate(DECK_COORD_R_X - SQUARE_SIZE / 2, DECK_COORD_R_Y + SQUARE_SIZE * 2.5);
+
+        displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);
+        root.getChildren().add(displayBoard);
+        displayBoard.setVisible(false);
+
+        // This section creates the radio buttons which allow player options to be selected
+        // Referenced http://docs.oracle.com/javafx/2/ui_controls/radio-button.htm while creating
+        RadioButton humanG = new RadioButton();
+        humanG.setToggleGroup(greenOptions);
+        humanG.setText("Human");
+        humanG.setUserData(Human);
+        humanG.disableProperty().bind(gameStarted);
+        humanG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 4);
+        humanG.setSelected(true);
+
+        RadioButton humanR = new RadioButton();
+        humanR.setToggleGroup(redOptions);
+        humanR.setText("Human");
+        humanR.setUserData(Human);
+        humanR.disableProperty().bind(gameStarted);
+        humanR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 4);
+        humanR.setSelected(true);
+
+        RadioButton easyG = new RadioButton();
+        easyG.setToggleGroup(greenOptions);
+        easyG.setText("Easy AI");
+        easyG.disableProperty().bind(gameStarted);
+        easyG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 5);
+        easyG.setUserData(Easy);
+
+        RadioButton easyR = new RadioButton();
+        easyR.setToggleGroup(redOptions);
+        easyR.setText("Easy AI");
+        easyR.disableProperty().bind(gameStarted);
+        easyR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 5);
+        easyR.setUserData(Easy);
+
+        RadioButton mediumG = new RadioButton();
+        mediumG.setToggleGroup(greenOptions);
+        mediumG.setText("Medium AI");
+        mediumG.disableProperty().bind(gameStarted);
+        mediumG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 6);
+        mediumG.setUserData(Medium);
+
+        RadioButton mediumR = new RadioButton();
+        mediumR.setToggleGroup(redOptions);
+        mediumR.setText("Medium AI");
+        mediumR.disableProperty().bind(gameStarted);
+        mediumR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 6);
+        mediumR.setUserData(Medium);
+
+        RadioButton hardG = new RadioButton();
+        hardG.setToggleGroup(greenOptions);
+        hardG.setText("Hard AI");
+        hardG.disableProperty().bind(gameStarted);
+        hardG.relocate(DECK_COORD_G_X - SQUARE_SIZE, DECK_COORD_G_Y + SQUARE_SIZE * 7);
+        hardG.setUserData(Hard);
+
+        RadioButton hardR = new RadioButton();
+        hardR.setToggleGroup(redOptions);
+        hardR.setText("Hard AI");
+        hardR.disableProperty().bind(gameStarted);
+        hardR.relocate(DECK_COORD_R_X - SQUARE_SIZE, DECK_COORD_R_Y + SQUARE_SIZE * 7);
+        hardR.setUserData(Hard);
+
+        Button startGame = new Button();
+        startGame.setText("Begin Game");
+        startGame.disableProperty().bind(gameStarted);
+        startGame.relocate(DECK_COORD_R_X - SQUARE_SIZE, BOARD_HEIGHT - 50);
+
+        root.getChildren().add(startGame);
+        root.getChildren().addAll(humanG, easyG, mediumG, hardG, humanR, easyR, mediumR, hardR);
+
+        Rectangle titleBack = new Rectangle((BOARD_WIDTH - 400) / 2, BOARD_HEIGHT / 4, 400, 300);
+        titleBack.setArcWidth(15);
+        titleBack.setArcHeight(15);
+        titleBack.setFill(Color.GRAY);
+        titleBack.setStrokeWidth(3);
+        titleBack.setStroke(Color.BLACK);
+        titleScreen.getChildren().add(titleBack);
+
+        Text titleText = new Text("Welcome to Stratopolis!");
+        titleText.setFont(new Font(26));
+        titleText.relocate((BOARD_WIDTH - titleText.getLayoutBounds().getWidth()) / 2, BOARD_HEIGHT / 3);
+        titleScreen.getChildren().add(titleText);
+
+        Text titleBody = new Text("- Use the radio buttons at the side to set up the game\n" +
+                "- Press M to play music\n" +
+                "- Press \"Start Game\" to begin\n");
+        titleBody.setWrappingWidth(360);
+        titleBody.relocate((BOARD_WIDTH - 360) / 2, BOARD_HEIGHT / 3 + 50);
+        titleBody.setFont(new Font(16));
+        titleScreen.getChildren().add(titleBody);
+
+        Rectangle overBack = new Rectangle((BOARD_WIDTH - 400) / 2, SQUARE_SIZE * 26 - 120, 400, 200);
+        overBack.setArcHeight(15);
+        overBack.setArcWidth(15);
+        overBack.setFill(Color.LIGHTGRAY);
+        overBack.setStrokeWidth(3);
+        overBack.setStroke(Color.BLACK);
+        overScreen.getChildren().add(overBack);
+
+        overText.setFont(new Font(36));
+        overBody.setFont(new Font(16));
+        overScreen.getChildren().addAll(overText, overBody);
+
+        root.getChildren().add(titleScreen);
+        root.getChildren().add(overScreen);
+        overScreen.setVisible(false);
+
+        remainingG = new Text();
+        remainingR = new Text();
+        root.getChildren().add(remainingG);
+        root.getChildren().add(remainingR);
+
+        // Sets up board when user requests game to start
+        startGame.setOnMouseReleased(event -> {
+            boardState = new BoardState("MMUA");
+            root.getChildren().remove(displayBoard);
+
+            overScreen.setVisible(false);
+
+            displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);
+            root.getChildren().add(displayBoard);
+
+            titleScreen.setVisible(false);
+
+            gameStarted.setValue(true);
+            greenMode = (PlayerMode) greenOptions.getSelectedToggle().getUserData();
+            redMode   = (PlayerMode) redOptions.getSelectedToggle().getUserData();
+
+            boardIndex = root.getChildren().indexOf(displayBoard);
+            displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10, (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);
+
+            greenScore.setText("Score: 1");
+            redScore.setText("Score: 1");
+
+            remainingG.setText("Pieces Remaining: 20");
+            remainingG.setFont(new Font(11));
+            remainingG.relocate(DECK_COORD_G_X - SQUARE_SIZE * 1.2, DECK_COORD_G_Y - SQUARE_SIZE / 3 - 10);
+
+            remainingR.setText("Pieces Remaining: 20");
+            remainingR.setFont(new Font(11));
+            remainingR.relocate(DECK_COORD_R_X - SQUARE_SIZE * 1.2, DECK_COORD_R_Y - SQUARE_SIZE / 3 - 10);
+
+            switch (redMode) {
+                case Human:
+                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,false);
+                    break;
+
+                case Easy:
+                    redAI = new EasyPlayer(true);
+                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,true);
+                    break;
+
+                case Medium:
+                    redAI = new MediumPlayer(true);
+                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,true);
+                    break;
+
+                case Hard:
+                    redAI = new HardPlayer(true);
+                    RDeck = new Deck(Colour.R,DECK_COORD_R_X, DECK_COORD_R_Y,true);
+                    break;
+            }
+
+            switch (greenMode) {
+                case Human:
+                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,false);
+                    break;
+
+                case Easy:
+                    greenAI = new EasyPlayer(false);
+                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,true);
+                    GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
+                    break;
+
+                case Medium:
+                    greenAI = new MediumPlayer(false);
+                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,true);
+                    GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
+                    break;
+
+                case Hard:
+                    greenAI = new HardPlayer(false);
+                    GDeck = new Deck(Colour.G,DECK_COORD_G_X, DECK_COORD_G_Y,true);
+                    GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
+                    break;
+            }
+        });
+
+        // Allow for music to be played when M is pressed
+        final AudioClip in = new AudioClip(Board.class.getResource("assets/bensound-goinghigher.mp3").toString());
+
+        //event handler for sound system
+        scene.setOnKeyPressed(event -> {
+            if(event.getCode() == KeyCode.M) {
+                if(soundOn) {
+                    in.stop();
+                    soundOn = false;
+                } else {
+                    in.play();
+                    soundOn = true;
+                }
+            }
+        });
+    }
+
+    private void hideHint() {
+        hint = null; // clear the hint group
+    }
+    // shows a hint, given by an easy AI
+    public void showHint(boolean forRedPlayer) {
+        hideHint();
+        if(hint ==null) {
+            EasyPlayer ep = new EasyPlayer(forRedPlayer);
+            String moveToShow;
+
+            if (forRedPlayer) {
+                moveToShow = ep.getBestMove(boardState, RDeck.getCurrentPiece(), GDeck.getCurrentPiece());
+            } else {
+                moveToShow = ep.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece());
+            }
+
+            //initialise a new hint
+            hint = new Group();
+
+            Pieces piece = new Pieces(moveToShow);
+
+            for (int i = 0; i < 3; i++) {
+                int x = piece.coords[i][0];
+                int y = piece.coords[i][1];
+
+                Colour colour = piece.colours[i];
+
+                Tile newTile = new Tile();
+                newTile.Stack(colour);
+
+                Group toAdd = (newTile).TileFX(SQUARE_SIZE);
+                toAdd.relocate(x * SQUARE_SIZE, y * SQUARE_SIZE);
+                hint.getChildren().add(toAdd);
+            }
+
+            //make this object fade out
+            root.getChildren().add(hint);
+            FadeTransition fade = new FadeTransition(Duration.seconds(6), hint);
+            fade.setFromValue(0.7);
+            fade.setToValue(0);
+            fade.play();
+        }
+    }
+
+    public void addPlacement(String placement) {
+        boardState.UpdateBoardGroup(displayBoard, SQUARE_SIZE, placement);
+    }
+
+
+    //A deck consists of a list of tiles. It has a subclass FXDraggablePiece which is essentially an imageview that
+    //can be dragged around by clicking and dragging. If the image is placed in a valid position on the board,
+    //it updates the boardstate to include the new piece, and makes it the opponents turn. Code for this
+    // was inspired by the drag and drop code used in assignment 1
+
+    public class Deck {
+
+        private char currentPieceOrientation;
+        private final int boardCoordX = (BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10;
+        private final int boardCoordY = (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10;
+        private char currentPieceType;
+        private char[] pieceArray;
+        private boolean green;
+        private boolean isAI;
+        private FXDraggablePiece icon;
+        private int piecesMarker = 0;
+
+        private static final String URI_BASE = "gui/assets/";
+
+        private int homeX, homeY;
+        private final int SIZE_OF_DECK = 46; // this is not the number of pieces in the deck, it is the physical size of the icon
+
+        char[] getPieceArray() {return pieceArray;}
+
+        int getPiecesMarker() {return piecesMarker;}
+
+        // Called by AIs to place pieces with animation
+        // http://docs.oracle.com/javafx/2/animations/basics.htm was referenced
+        // in the creation of this method, in particular to figure out how to write animations
+        void AIPlace(String newPiece) {
+            // Used to offset the translation animation to account for rotation
+            int xOff = 0, yOff = 0;
+
+            if (newPiece.charAt(3) < 'D' && newPiece.charAt(3) > 'A') {
+                xOff = -SQUARE_SIZE;
+            }
+            if (newPiece.charAt(3) > 'B') {
+                yOff = -SQUARE_SIZE;
+            }
+
+            icon.setOpacity(0.5);
+            TranslateTransition translation = new TranslateTransition(Duration.millis(1000), icon);
+            translation.setToX((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 + (newPiece.charAt(0) - 'A') * SQUARE_SIZE - homeX + xOff);
+            translation.setToY((BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 + (newPiece.charAt(1) - 'A') * SQUARE_SIZE - homeY + yOff);
+
+            RotateTransition rotation = new RotateTransition(Duration.millis(400), icon);
+            rotation.setByAngle((newPiece.charAt(3) - 'A') * 90);
+
+            ParallelTransition move = new ParallelTransition();
+            move.getChildren().addAll(translation, rotation);
+            // Updates the board once animation is finished
+            move.setOnFinished(event -> {icon.setOpacity(1); placePiece(newPiece); icon.setRotate(0); icon.setTranslateX(0); icon.setTranslateY(0);});
+
+            move.play();
+        }
+
+        // Places a piece on the board and changes the active player
+        void placePiece(String newPiece) {
+            // Computations are performed in a separate thread so that UI may be updated
+            // while computations are occurring. Referenced http://stackoverflow.com/questions/26554814/javafx-updating-gui
+            // for this section.
+            Task<Void> task = new Task<Void>() {
+                @Override
+                public Void call() throws Exception {
+                    piecesMarker++;
+                    if (piecesMarker < 20) {
+                        currentPieceType = pieceArray[piecesMarker];
+
+                        // Sections which update the UI are given to the main
+                        // thread to be executed concurrently
+                        FutureTask<Void> UpdateUI = new FutureTask<>(() -> {
+                            addPlacement(newPiece);
+                            icon.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));
+                        }, null);
+
+                        Platform.runLater(UpdateUI);
+                        UpdateUI.get();
+                    } else {
+                        FutureTask<Void> UpdateUI = new FutureTask<>(() -> {
+                            addPlacement(newPiece);
+                            icon.setImage(null);
+                        }, null);
+
+                        Platform.runLater(UpdateUI);
+                        UpdateUI.get();
+
+                        // When red plays last piece, end of game is triggered
+                        // Section brings up statement of results and allows new game to be started
+                        if (!green) {
+                            FutureTask<Void> EndGame = new FutureTask<>(() -> {
+                                overScreen.toFront();
+                                overScreen.setVisible(true);
+
+                                if (boardState.BoardScore(true) > boardState.BoardScore(false)) {
+                                    overText.setText("Green Wins!");
+                                    overText.setFill(Color.DARKGREEN);
+                                    overText.setStrokeWidth(1);
+                                    overText.setStroke(Color.BLACK);
+                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
+                                            SQUARE_SIZE * 26 - 100);
+                                } else if (boardState.BoardScore(false) > boardState.BoardScore(true)) {
+                                    overText.setText("Red Wins!");
+                                    overText.setFill(Color.DARKRED);
+                                    overText.setStrokeWidth(1);
+                                    overText.setStroke(Color.BLACK);
+                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
+                                            SQUARE_SIZE * 26 - 100);
+                                } else if (boardState.BreakTie()) {
+                                    overText.setText("Green Wins!");
+                                    overText.setFill(Color.DARKGREEN);
+                                    overText.setStrokeWidth(1);
+                                    overText.setStroke(Color.BLACK);
+                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
+                                            SQUARE_SIZE * 26 - 100);
+                                } else {
+                                    overText.setText("Red Wins!");
+                                    overText.setFill(Color.DARKRED);
+                                    overText.setStrokeWidth(1);
+                                    overText.setStroke(Color.BLACK);
+                                    overText.relocate((BOARD_WIDTH - overText.getLayoutBounds().getWidth()) / 2,
+                                            SQUARE_SIZE * 26 - 100);
+                                }
+
+                                overBody.setText("                       Final Scores:\n           " +
+                                        "    Green: " + boardState.BoardScore(true) + "      Red: " + boardState.BoardScore(false) + "\n" +
+                                        "Use radio buttons to set up next game \n" +
+                                        "      Press \"Start Game\" to play again");
+                                overBody.relocate((BOARD_WIDTH - overBody.getLayoutBounds().getWidth()) / 2, SQUARE_SIZE * 26 - 40);
+
+                                gameStarted.set(false);
+                            }, null);
+                            Platform.runLater(EndGame);
+                            EndGame.get();
+                        }
+                    }
+
+                    FutureTask<Void> UpdateUI = new FutureTask<>(() -> {
+                        greenScore.setText("Score: " + boardState.BoardScore(true));
+                        redScore.setText("Score: " + boardState.BoardScore(false));
+
+                        if (green) {
+                            remainingG.setText("Pieces Remaining: " + (20 - piecesMarker));
+                        } else {
+                            remainingR.setText("Pieces Remaining: " + (20 - piecesMarker));
+                        }
+                    }, null);
+
+                    Platform.runLater(UpdateUI);
+                    UpdateUI.get();
+
+                    greensTurn = !greensTurn;
+                    // Automates the AI players
+                    if (greensTurn && GDeck.getAI() && GDeck.piecesMarker < 20) {
+                        GDeck.AIPlace(greenAI.getBestMove(boardState, GDeck.getCurrentPiece(), RDeck.getCurrentPiece()));
+                    } else if (!greensTurn && RDeck.getAI() && RDeck.piecesMarker < 20) {
+                        RDeck.AIPlace(redAI.getBestMove(boardState, RDeck.getCurrentPiece(), GDeck.getCurrentPiece()));
+                    }
+
+                    return null;
+                }
+            };
+            new Thread(task).start();
+        }
+
+
+
+
+        //the draggable part of the deck - this class was inspired by the draggable functionality implemented
+        //in the source code for assignment one
+
+        private class FXDraggablePiece extends ImageView {
+            //the mouse coordinates X and Y
+            double mouseX, mouseY;
+
+            FXDraggablePiece (char pieceType, double size, int x, int y) {
+                //retrieves the image for the piece and then sets the image for the deck, for the user to drag
+                //to the board
+                Image deckImage = new Image(BoardState.class.getResource(URI_BASE + pieceType + ".png").toString());
+                setImage(deckImage);
+                root.getChildren().add(this);
+
+                setLayoutX(homeX);
+                setLayoutY(homeY);
+
+                setFitHeight(size);
+                setFitWidth(size);
+
+                // scroll to change orientation
+                setOnScroll(event -> {
+                    if (greensTurn == green && !isAI) { // if the deck is an AI deck then the dragging is not needed
+                        double rotation = this.getRotate();
+                        //rotates the piece by adding 90 to the rotation and taking %360 to deal
+                        //with rotations over 360 degrees
+                        this.setRotate((rotation + 90) % 360);
+                        if (getRotate() == 0) {
+                            currentPieceOrientation = 'A';
+                        } else if (getRotate() == 90) {
+                            currentPieceOrientation = 'B';
+                        } else if (getRotate() == 180) {
+                            currentPieceOrientation = 'C';
+                        } else if (getRotate() == 270) {
+                            currentPieceOrientation = 'D';
+                        }
+                    }
+                    event.consume();
+                });
+                // mouse press indicates begin of drag. Here we update the coordinates of mouseX and MouseY
+                // so that they correspond to the piece
+                setOnMousePressed(event -> {
+                    if (green == greensTurn && !isAI) {
+                        mouseX = event.getSceneX();
+                        mouseY = event.getSceneY();
+                        setOpacity(0.5);
+                    }
+
+                });
+                // Here we update the coordinates of the FXDraggablePiece by tracking it
+                // with the mouse coordinates so the user can drag it directly onto the board
+                setOnMouseDragged(event -> {
+                    //check
+                    if (green == greensTurn && !isAI) {
+
+                        //make it so that if the mouse hovers over a valid position the position lights up
+
+                        double movementX = event.getSceneX() - mouseX;
+                        double movementY = event.getSceneY() - mouseY;
+                        setLayoutX(getLayoutX() + movementX);
+                        setLayoutY(getLayoutY() + movementY);
+
+                        mouseX = event.getSceneX();
+                        mouseY = event.getSceneY();
+                    }
+                    event.consume();
+                });
+                //code to place the piece - the mouse button has been released
+                setOnMouseReleased(event -> {
+                    //check whose turn it is
+                    setOpacity(1.0);
+                    if (green == greensTurn && !isAI) {
+                        //these calculations were inspired by assignment 1
+                        int xDrop = (int) this.getLayoutX();
+                        int yDrop = (int) this.getLayoutY();
+                        int squareX = (int) (xDrop - boardCoordX) / 23;
+                        int squareY = (int) (yDrop - boardCoordY) / 23;
+
+
+                        //coordinate characters A-Z that will form part of the placement
+                        char yLetter;
+                        char xLetter;
+                        //quick check as to whether the user has placed the square on the board
+                        if (squareX < 26 && 0 <= squareX && 0 <= squareY && squareY < 26) {
+                            //this switch ensures that the pieces are placed correctly given their orientation,
+                            //which may cause the player to drag them onto the board slightly differently
+                            switch (currentPieceOrientation) {
+
+                                case 'A':
+                                    break; //nothing to be done
+
+                                case 'B':
+                                    squareX++;
+                                    break;
+                                case 'C':
+                                    squareX++;
+                                    squareY++;
+                                    break;
+                                case 'D':
+                                    squareY++;
+                                    break;
+                            }
+
+                            xLetter = (char) ('A' + squareX);
+                            yLetter = (char) ('A' + squareY);
+
+                            String newPiece = "" + xLetter + yLetter + currentPieceType + currentPieceOrientation;
+
+                            if (boardState.IsValidMove(newPiece)) {
+
+
+                                placePiece(newPiece);
+
+                            }
+                        }
+                        //return the image to its usual orientation
+                        this.setRotate(0);
+                        currentPieceOrientation = 'A';
+                        //return the draggable piece to its home
+                        setLayoutX(homeX);
+                        setLayoutY(homeY);
+                    }
+                    event.consume();
+                });
+            }
+        }
+
+        char getCurrentPiece() {
+            if (piecesMarker < 20) {
+                return pieceArray[piecesMarker];
+            } else {
+                // Invalid character, will cause operations which require
+                // a piece to finish up
+                return 'n';
+            }
+        }
+
+        boolean getAI() { return isAI;}
+
+        Deck(Colour alignment, int x, int y, boolean isAi) {
+            char[] deck;
+            homeX = x;
+            homeY = y;
+            isAI = isAi;
+
+            green = Colour.G == alignment;
+
+            //create an array for the deck
+            if (!green) {
+                deck = new char[] {'A','B','C','D','E','F','G','H','I','J','A','B','C','D','E','F','G','H','I','J'};
+            } else  {
+                deck = new char[] {'K','L','M','N','O','P','Q','R','S','T','K','L','M','N','O','P','Q','R','S','T'};
+            }
+
+            pieceArray = shuffle(deck);
+
+            //make the piece at the front of the deck appear on screen by initialising a new FXDraggablePiece
+            currentPieceOrientation = 'A';
+            currentPieceType = pieceArray[0];
+            icon = new FXDraggablePiece(currentPieceType,SIZE_OF_DECK,x,y);
+        }
+    }
+
+    //algorithm that shuffles a list
+    private char[] shuffle(char[] list) {
+
+        Random random = new Random();
+
+        for(int i = list.length - 1; i >= 0; i--) {
+            int rand = random.nextInt(i + 1);
+
+            char temp = list[rand];
+            list[rand] = list[i];
+            list[i] = temp;
+
+        }
+        return list;
+    }
+
+
+    //HardPlayer is a 3 lookhead minimax algorithm with alpha beta pruning. It is an inner class of the board,
+    //as it needs to access the next deck piece coming up, unlike the other bots.
+    public class HardPlayer implements Player {
+        boolean redIsPlaying;
+        char opponentDeckPiece;
+        private final int MAX_LOOKAHEAD = 2;
+
+        public HardPlayer(boolean redIsPlaying) {
+            this.redIsPlaying = redIsPlaying;
+        }
+
+        public String getBestMove(BoardState board, char currentDeckPiece, char opponentDeckPiece) {
+            this.opponentDeckPiece = opponentDeckPiece;
+            ArrayList<BoardState> possibleBoards = generateNextBoards(board, redIsPlaying, currentDeckPiece);
+            BoardState bestBoard = possibleBoards.get(0);
+            int bestBoardVal = alphaBeta(bestBoard, -1000, 1000, MAX_LOOKAHEAD, !redIsPlaying);
+            int moveNumber = 0; //keeps track of the index of the best move, so it can be returned
+            for (int i = 0; i < possibleBoards.size(); i++) {
+                int testValue = alphaBeta(possibleBoards.get(i), -1000, 1000, MAX_LOOKAHEAD, !redIsPlaying);
+                //since we are taking alpha-beta of "!redIsPlaying" we have to minimise on this node.
+                //so bestboardval is the minimum
+                if (bestBoardVal > testValue)  {
+                    bestBoardVal = testValue;
+                    moveNumber = i;
+                }
+            }
+            bestBoard = possibleBoards.get(moveNumber);
+            return bestBoard.GetBoard().substring(bestBoard.GetBoard().length() - 4);
+        }
+
+        //minimax alpha-beta algorithm
+        private int alphaBeta(BoardState board, int alpha, int beta, int lookahead, boolean maximiseForRed) {
+            int bestValue;
+            ArrayList<String> movesList;
+
+            if (lookahead == 0  || gameOverQuery(board)) { //if we run out of lookaheads or the game is over we simply evaluate the board
+                bestValue = evaluateBoard(board, maximiseForRed);
+            } else {
+                if (lookahead == MAX_LOOKAHEAD) { //if we are on the first lookahead, use the opponent deck piece
+                    movesList = board.generateAllPossibleMoves(maximiseForRed, opponentDeckPiece);
+                } else {
+                    if (maximiseForRed) {
+                        //here is where the bot cheats: it looks ahead through the moves that it knows it will be able to make, given the deckpiece its opponent cant see
+                        movesList = board.generateAllPossibleMoves(true, RDeck.getPieceArray()[RDeck.getPiecesMarker() + 1]);
+                    } else {
+                        movesList = board.generateAllPossibleMoves(false, GDeck.getPieceArray()[GDeck.getPiecesMarker() + 1]);
+                    }
+                }
+                if (maximiseForRed) {
+                    //set the bestvalue to our alpha (since it is the best value discovered)
+                    bestValue = alpha;
+
+                    //iterate through the moves list
+                    for (String aMove : movesList) {
+                        //generate a new boardstate to test the move (so we dont change the actual board)
+                        BoardState tBoard = new BoardState(board.GetBoard());
+                        //place a tile on the board
+                        tBoard.PlaceTile(aMove);
+                        //find the alpha beta of the child value
+                        int childValue = alphaBeta(tBoard, bestValue, beta, lookahead - 1, false);
+                        bestValue = Math.max(bestValue, childValue);
+                        if (beta <= bestValue) {
+                            break;
+                        }
+                    }
+                } else {
+                    bestValue = beta;
+
+                    for (String aMove : movesList) {
+                        BoardState tBoard = new BoardState(board.GetBoard());
+                        tBoard.PlaceTile(aMove);
+                        int childValue = alphaBeta(tBoard, alpha, bestValue, lookahead - 1, true); //call alphabeta again with the new parameters
+                        bestValue = Math.min(bestValue, childValue);
+                        if (bestValue <= alpha) {
+                            break;
+                        }
+                    }
+                }
+            }
+            return bestValue;
+
+        }
+
+        private ArrayList<BoardState> generateNextBoards(BoardState board, boolean isRedsTurn, char deckPiece) {
+            ArrayList<BoardState> toReturn = new ArrayList<>();
+            ArrayList<String> movesList = board.generateAllPossibleMoves(isRedsTurn, deckPiece);
+            for (String s : movesList) {
+                    BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
+                    tBoard.PlaceTile(s);
+                    toReturn.add(tBoard);
+            }
+
+            return toReturn;
+        }
+
+        private int evaluateBoard(BoardState board, boolean isRedsTurn) {
+            return board.BoardScore(!isRedsTurn)-board.BoardScore(isRedsTurn);
+        }
+
+
+    }
+    //plays games over and over again and prints them to the log
+    //used this for machine learning extension
+    private void play_n_sample(int numberOfGames) {
+            //sets up the AI based on what the player wants
+            EasyPlayer ep1 = new EasyPlayer(true);
+            //HardPlayer hp = new HardPlayer(false);
+            EasyPlayer ep2 = new EasyPlayer(false);
+            MediumPlayer mp = new MediumPlayer(true);
+            MediumPlayer mp2 = new MediumPlayer(false);
+            NN1HL n = new NN1HL(8,676,1,0.001);
+            HardPlayer hp = new HardPlayer(false);
+            IntelligentPlayer ip = new IntelligentPlayer(n);
+            MonteCarloPlayer mcp = new MonteCarloPlayer(true);
+            RandomPlayer rand = new RandomPlayer(true);
+
+            // int NO_OF_GAMES = 1;
+            for (int i = 0; i < numberOfGames; i++) {
+                boardState = new BoardState("MMUA");
+                root.getChildren().remove(boardIndex);
+                displayBoard = boardState.GetBoardGroup(SQUARE_SIZE);
+                displayBoard.relocate((BOARD_WIDTH - SQUARE_SIZE * 26) / 2 - 10, (BOARD_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);
+                root.getChildren().add(displayBoard);
+                boardIndex = root.getChildren().indexOf(displayBoard);
+
+                ArrayList<String> boards = new ArrayList<>();
+                ArrayList<Integer> outcomes = new ArrayList<>();
+
+                //generate a new deck
+                char[] Rdeck;
+                char[] Gdeck;
+
+                Rdeck = new char[] {'A','B','C','D','E','F','G','H','I','J','A','B','C','D','E','F','G','H','I','J'};
+
+                Gdeck = new char[] {'K','L','M','N','O','P','Q','R','S','T','K','L','M','N','O','P','Q','R','S','T'};
+
+
+                //shuffle the deck
+                char[] RpieceArray = shuffle(Rdeck);
+                char[] GpieceArray = shuffle(Gdeck);
+
+
+                RDeck.pieceArray = RpieceArray;
+                GDeck.pieceArray = GpieceArray;
 
-    // FIXME Task 8: Implement a basic playable Strato Game in JavaFX that only allows pieces to be placed in valid places
 
-    // FIXME Task 9: Implement scoring
+                for (int piecesPlayed = 0; piecesPlayed < 20; piecesPlayed++) {
+                    //pair[0] for the green player
+                    //pair[1] for the red player
 
-    // FIXME Task 11: Implement a game that can play valid moves (even if they are weak moves)
+                    RDeck.placePiece(mcp.getBestMove(boardState,RDeck.getCurrentPiece(),GDeck.getCurrentPiece()));
+                    boards.add(boardState.GetBoard());
+                    GDeck.placePiece(ep2.getBestMove(boardState, GDeck.getCurrentPiece(),RDeck.getCurrentPiece()));
+                    boards.add(boardState.GetBoard());
 
-    // FIXME Task 12: Implement a game that can play good moves
+                    System.out.println("Red's score is currently "+boardState.BoardScore(false));
+                    System.out.println("Green's score is currently "+boardState.BoardScore(true));
 
-    @Override
-    public void start(Stage primaryStage) throws Exception {
+                    if (piecesPlayed == 19) {
+                        if (boardState.BoardScore(true) < boardState.BoardScore(false)) {
+                            // System.out.println("green won");
+                            //go back and assign 1 to all of greens moves and -1 to all of reds moves
+                            for(int boardNumber = 0; boardNumber<boards.size();boardNumber++) {
+                                //since green went first assign every even number 1 and every odd -1
+                                if(boardNumber%2 == 0) {
+                                    outcomes.add(-1);
+                                } else {
+                                    outcomes.add(1);
+                                }
+                            }
+                        } else {
+                            //System.out.println("red won");
+                            for(int boardNumber = 0; boardNumber<boards.size();boardNumber++) {
+                                //since green went first assign every even number -1 and every odd 1
+                                if(boardNumber%2 == 0) {
+                                    outcomes.add(1);
+                                } else {
+                                    outcomes.add(-1);
+                                }
+                            }
+                        }
+                    }
+                }
+                System.out.println(boards);
+                System.out.println(outcomes);
+                System.out.println(boardState.GetBoard());
+            }
+        }
 
+    private boolean gameOverQuery(BoardState board) {
+        return board.GetBoard().length()==164; //a boardstate after a complete game has length 168
     }
 }
diff -ru -x .git ../master/comp1140-ass2/src/comp1110/ass2/gui/Viewer.java comp1140-ass2/src/comp1110/ass2/gui/Viewer.java
--- ../master/comp1140-ass2/src/comp1110/ass2/gui/Viewer.java	2016-10-15 13:59:26.000000000 +1100
+++ comp1140-ass2/src/comp1110/ass2/gui/Viewer.java	2016-10-15 14:05:54.000000000 +1100
@@ -1,5 +1,6 @@
 package comp1110.ass2.gui;
 
+import comp1110.ass2.BoardState;
 import javafx.application.Application;
 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
@@ -23,13 +24,14 @@
     /* board layout */
     private static final int VIEWER_WIDTH = 750;
     private static final int VIEWER_HEIGHT = 700;
-
-    private static final String URI_BASE = "assets/";
+    private static final int SQUARE_SIZE = 23;
 
     private final Group root = new Group();
     private final Group controls = new Group();
     TextField textField;
 
+    private Group current = null;
+
 
     /**
      * Draw a placement in the window, removing any previously drawn one
@@ -37,10 +39,18 @@
      * @param placement  A valid placement string
      */
     void makePlacement(String placement) {
+        root.getChildren().remove(current);
+        BoardState board = new BoardState(placement);
+
+        current = board.GetBoardGroup(SQUARE_SIZE);
+
+        root.getChildren().add(current);
+        current.relocate((VIEWER_WIDTH - SQUARE_SIZE * 26) / 2 - 10,(VIEWER_HEIGHT - SQUARE_SIZE * 26 - 50) / 2 - 10);
+
+        System.out.println(board.BoardScore(true));
         // FIXME Task 5: implement the simple placement viewer
     }
 
-
     /**
      * Create a basic text field for input and a refresh button.
      */
@@ -70,7 +80,6 @@
         Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
 
         root.getChildren().add(controls);
-
         makeControls();
 
         primaryStage.setScene(scene);
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: Black.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: Green.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: Red.png
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: Samples
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: alphas.txt
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: bensound-goinghigher.mp3
Only in comp1140-ass2/src/comp1110/ass2/gui/assets: betas.txt
Only in comp1140-ass2/src/comp1110/ass2: la4j
Only in comp1140-ass2/src: gittest
Only in comp1140-ass2/tests/comp1110/ass2: MoveValidTest.java
Only in comp1140-ass2/tests/comp1110/ass2: PiecesTest.java
Only in comp1140-ass2: uml.png
```
## Test log
```
--javac output--
javac: file not found: comp1140-ass2/src/comp1140/ass2/*.java
Usage: javac <options> <source files>
use -help for a list of possible options
javac: file not found: comp1140-ass2/src/comp1140/ass2/gui/*.java
Usage: javac <options> <source files>
use -help for a list of possible options
----
java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.TilePlacementWellFormedTest
---
JUnit version 4.12
.E
Time: 0.003
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.TilePlacementWellFormedTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.TilePlacementWellFormedTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.PlacementWellFormedTest
---
JUnit version 4.12
.E
Time: 0.003
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.PlacementWellFormedTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.PlacementWellFormedTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.PlacementValidTest
---
JUnit version 4.12
.E
Time: 0.003
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.PlacementValidTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.PlacementValidTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.ScoreFromPlacementTest
---
JUnit version 4.12
.E
Time: 0.004
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.ScoreFromPlacementTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.ScoreFromPlacementTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

java -cp comp1140-ass2/src:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/ass2-1140-tests.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/hamcrest-core-1.3.jar:/Users/benjamin/Uni/COMP1140/comp1110-tutors/bin/../ass2/junit-4.12.jar org.junit.runner.JUnitCore comp1140.ass2.GenerateMoveTest
---
JUnit version 4.12
.E
Time: 0.007
There was 1 failure:
1) initializationError(org.junit.runner.JUnitCommandLineParseResult)
java.lang.IllegalArgumentException: Could not find class [comp1140.ass2.GenerateMoveTest]
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:102)
	at org.junit.runner.JUnitCommandLineParseResult.parseArgs(JUnitCommandLineParseResult.java:50)
	at org.junit.runner.JUnitCommandLineParseResult.parse(JUnitCommandLineParseResult.java:44)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:72)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
Caused by: java.lang.ClassNotFoundException: comp1140.ass2.GenerateMoveTest
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.junit.internal.Classes.getClass(Classes.java:16)
	at org.junit.runner.JUnitCommandLineParseResult.parseParameters(JUnitCommandLineParseResult.java:100)
	... 4 more

FAILURES!!!
Tests run: 1,  Failures: 1

```
