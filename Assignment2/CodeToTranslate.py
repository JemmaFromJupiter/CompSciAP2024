# Instructions: Below is a simple number guessing game written in Python. Rewrite the program in Java such that it functions identically. The clear() method has already been made for you. 


from random import randint
from os import system 

#Clears the console
def clear():
  system("clear")

#Input verification for getting an integer from the user
#message: Prompt displayed to user when asking for input
#min: Minumum allowed value
#max: Maximum allowed value
#returns integer in specified range
def getNumber(message,min,max):
  while True:
    try:
      number = int(input(message))
    except ValueError:
      print("Error; that's not a number\n")
      continue
    if number < min:
      print(f"Error; must be at least {min}\n")
    elif number > max:
      print(f"Error; must be at most {max}\n")
    else:
      return number  
      
   
#Feedback function telling the player whether they guessed too high or too low
#guess: The player's guess
#number: The secret number
#returns response as string
def higherLower(guess, number):
  if guess > number:
    return "You guessed too high"
  return "You guessed too low"

#Feedback function telling the player whether they are close to the secret number
#guess: The player's guess
#number: The secret number
#returns response as string
def hotCold(guess, number):
  difference = abs(guess - number)
  
  if difference == 1:
    return "You're blazing hot!"
  elif difference <= 3:
    return "You're hot!"
  elif difference <= 6:
    return "You're warm..."
  return "You're cold."

#Feedback function telling the player they didn't guess correctly
#guess: The player's guess (not used)
#number: The secret number (not used)
#returns response as string
def noFeedback(guess, number):
  return "Nope, that's not it"

# NOTE: Because Java doesn't allow functions to be passed as a parameter or return value, you will need to implement this part in a different way. I recommend creating a variable that stores what kind of feedback they want as an integer and use a switch or if block to choose which function should be called when giving feedback after a wrong guess. 
  
#Sets the function that should be used when giving the player feedback
#returns function that will generate feedback for the player
def setFeedback():
  feedback = getNumber("What kind of feedback would you like to help?\n\n1) Higher/Lower\n2) Hot/Cold\n3) None\n\n",1,3)

  match feedback:
    case 1:
      return higherLower
    case 2:
      return hotCold
    case 3:
      return noFeedback

#generates the upper limit and number of turns based on player choice for difficulty
#returns upper limit, number of guesses as integers
def setDifficulty():
  number = getNumber("Choose a difficulty level (1-3): ",1,3)
  return number*10,number+2

#plays one full round of the game
#returns whether to play another round as boolean
def playGame():
  clear()
  #initializing values and feedback function for start of a new round
  upperLimit, numberOfTurns = setDifficulty()
  feedback = setFeedback()
  number = randint(1,upperLimit)
  clear()
  
  #Inner loop for each guess from player
  for turnsRemaining in range(numberOfTurns,0,-1):
    print(f"You have {turnsRemaining} turns remaining\n")
    print(f"I'm thinking of a number between 1 and {upperLimit}")
    guess = getNumber("What do you think it is? ",1,upperLimit)
  
    #checking if guessed correctly and stopping the loop if it is
    if guess == number:
      print("\nYou got it!")
      break
  
    clear()
    #give feedback on their guess
    print(feedback(guess,number))
  
  #If player didn't guess correct on last attempt, tell them what the correct number was
  if guess != number:
    print(f"\nYou lost!\nThe number was {number}")
  
  #returns boolean on whether to play another round
  return getNumber("\nDo you want to play again?\n1 for yes, 2 for no: ",1,2) == 1


if __name__ == "__main__":
  
  #Introducing the game
  print("Welcome to the guessing game! Try to guess the number I'm thinking of to win!\n\n")
  
  #while loop to play multiple rounds
  while (playGame()):
    pass
  
  clear()
  print("Goodbye!")