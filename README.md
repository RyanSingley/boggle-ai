boggle-ai
=========

A Boggle clone with a computer player.

Boggle is a word game that has a randomized board of letters, arranged in a 4x4 grid.  A player scores points by making words from a path on the board.  Paths may include diagonal directions.  No space may be used twice in the same word, and words must be at least 3 characters in length.

This version differs from the original in scoring.  The player is given the advantage of going first, and any words found by the player are unavailible to the computer opponent.

Implementation:

The board is represented in memory by a bidirectional isometric graph structure with links contained in a list within each node.  References to board spaces are also availible as an array.  The dictionary of words(currently ~170,000) is read from a text file an arranged as a multidimensional array.

Words on the board are verified by a form of depth first search from every instance of the starting letter.  The computer player operates by checking each word in the dictionary against the board.  This completes rather fast due to the tendency of the search to end quickly(most times the DFS searches to a depth of 2 or less).

The user iterface(not completed) will utilize MVC architecture, with a console view and simple keyboard based controller.
