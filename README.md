# Stratopolis - A strategy boardgame.

A boardgame implementing player vs player modes and player vs computer modes. 

##Features include

- A simple placement viewer
- A basic playable strato game
- A basic playable strato game that snaps pieces to the board and checks for validity
- Heights of regions indicated
- Running scores
- Basic computer opponent
- Advanced computer opponent

##additional features...

- concurrent scoring algorithm
- neural network trained opponenet
- monte carlo tree search player
- (bad) music (press m)
- advanced computer opponents easy, medium and hard
- animated AI piece placement
- pretty UI

## Description of AI algorithms, and how to test them for yourself (author: Tom Hamer)

The "HardPlayer" in the Board class implements alpha beta pruning to search the game tree. This AI beats
my two other AIs - the easy AI (one lookahead) and the medium AI (two lookaheads). We also verified that medium beats easy and hard beats medium, which is good.

However, the hard AI cheats and finds out which piece the player has next. This was not ideal, as it is important for user satisfaction
that the bot wins on its own merits rather than by cheating.

Also, especially toward the end of the game, the calculation time that the hard AI spens is too excessive. It can take up to 30 seconds to return a move.

To test the neural network and monte carlo, you can use PlayGames.java, as they were left out of the main interface due to fear of cluttering the UI.
Using that class you can see how matches play out in the log.

a) IntelligentPlayer.java:

- Trained to find good moves using a one hidden layer neural network, the trained values are parsed in and put into a matrix
- only trained for red
- Alphas.txt and Betas.txt contain the trained values
- See Trainer.java, Samples.txt and PlayGames
- play_n_sample was used to play lots and lots of games between two easy bots and print out the data
- the data was put into samples, where it was read in by trainer, put into a matrix, and a neural network was trained
- The neural network is in NN1HS.java
- Boards were converted into a 676 length vector with red values as 1, green values as -1 and everything else as 0 (this was limiting and will be discussed)
- It is entirely my own work and implementation of a java neural network. The backpropagation equations were taken from
The Elements of Statistical Learning (Trevor Hastie)

Sadly, while the network is functioning, it did not work very well on the strato game data.
This appears to be due to a few reasons:

- The lack of convolutional layers meant that the network had to crunch a 27000 by 676 matrix as input. This meant that backpropogation
took a long time, and it made testing different learning rates, biases, and validation very slow, time consuming and frustrating (it took about an hour to get one backpropagation step)
- Further, there is evidence to suggest that it could be impossible for a network without two hidden layers to learn Strato.
- Also, the vectorisation of the board probably does not convey sufficient information to effectively train the network.
A good improvement would be to have a channel for each colour, and to have the height as the values, with everything else 0, similar to the vectorisation of an RGB coloured image.
Playing chess seems to require two hidden layers, and in many ways the problem of strato is similar to chess (reference http://cs231n.stanford.edu/reports/ConvChess.pdf)
- A better implementation, if I had time I would have implemented the architecture Conv-relu affine*2, where there is a convolutional layer first
which reduces the training time, and a second fully connected layer to get more accurate high level reasoning.


b) MonteCarloPlayer.java
- Since the GO Monte Carlo method was so successful, and strato is a similar game to go, I decided to build a monte carlo tree
search algorithm
- the Monte Carlo algorithm is not particularly good as generating a random move is relatively intensive with our implementation.
- this means that the AI cannot play out enough games to make good estimates of what a good move actually is.
- a way this could be improved would be rather than generating all the moves, to generate a list of valid tiles from the baord state class
and guess random piece orientations until a valid move is found.
- That being said, it does beat a random player, especially at the end when the bot can easily tell whether a piece placement
leads to a win or a loss.
- based on this implementation: https://jeffbradberry.com/posts/2015/09/intro-to-monte-carlo-tree-search/


TO TRY DIFFERENT PLAY-OUTS BETWEEN EXPERIMENTAL PLAYERS USE THE CLASS PLAYGAMES!

The results will log to the console. (Only the bots that work well and reliably are listed in the actual game as options)

I declare that this is entirely my own work, with the following documented exceptions:

The exceptions are mentioned above, and in G-originality.md



##Built with

- JavaFX ... http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
- La4j ... http://la4j.org



Authors: Tom Hamer, Calum Snowdon
UI by Calum Snowdon, implementation coded jointly between Tom Hamer and Calum Snowdon, AI by Tom Hamer.
