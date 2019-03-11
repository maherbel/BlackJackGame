This is a Game Factory where multiple games can be played (BlackJack, Poker, etc...)
- For now only BlackJack is supported.
***************************************************************************************

BlackJack Game supported features :
- Playing with one or multiple players against the dealer
- The Stand/Hit/Double/Surrender actions are supported
- Playing again
- Extensible (design) so that others games can be implemented in the future

BlackJack Game unsupported features :
- The Split and Insurance actions are not supported yet.
- The scoring system design is present but not implemented yet

***************************************************************************************

Suggestions to improve the project technically :
- Testing of the engine methods
- Definition of a printer utility to be able to print any output given a player and a message
  because the System.out.println consumes a lot of cpu
- The BlackJackActionsEngine should be abstracted into a GameActionsEngine that could be
  implemented by BlackJackActionsEngine as it will exist a similar "Actions" concept for the other games

***************************************************************************************
***************************************************************************************
Rules of BlackJack :

Number of Players: Any number may play.

Rank of Cards: Cards have no relative rank but the counting value is as follows: K’s, Q’s, and J’s, 10 each;
Aces, 11 or 1, balance are counted at pip value, 9’s 9, 8’s 8, etc.

Betting Before the Deal: Before cards are dealt each player, except the dealer,
makes a bet placing the counters or chips before him.
Dealer makes no bet, but is the banker, who takes and pays all player’s bets.

Object of the Game: To hold cards, the collective pip value of which most nearly approaches 21,
without passing that number.

Dealing: Dealer gives each player two cards one at a time.

Drawing and Settling of Bets: Each player examines cards dealt him.
If dealer’s cards consist of an Ace and 10 (or Court card) it is called a Natural,
and each player (unless he also has a Natural) loses twice the amount he has staked.
Should a player have a Natural and dealer none, dealer must pay player double.

If no player receives a Natural (or after players [other than dealer] have been paid for Naturals held),
each player in turn may ask for a card so as to bring the pip value of his hand nearer to 21.
Drawing begins with eldest hand, and he may draw one card at a time until he is satisfied,
or until the pip value of his hand exceeds 21. In latter case he must abandon his hand and pay his stake to dealer.

Next player draws in same manner, and so on until each player is either satisfied or overdrawn.
The dealer then draws. If dealer overdraws, he pays each player who has not overdrawn the amount of that player’s stake.
If dealer has 21 or less, players having same amount are tied and neither win nor lose,
those holding less lose their stake,
while those holding more than dealer but not more than 21 win the amount of their stakes.