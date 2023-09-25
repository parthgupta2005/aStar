/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AStarAlgorithm
{

  public static ArrayList<State> AStarAlgorithm(int[][] initialState)
  {
    int numberOfElements = initialState.length * initialState[0].length;
    int i;
    int j;
    int k;
    int rowVal = 0;
    boolean checkAdd = false;
    boolean checkParent = true;
    int inversions = 0;
    State nextMove;
    State nextState;
    ArrayList<Integer> checkInversions = new ArrayList<>();

    ArrayList<Integer> checkGoal = new ArrayList<>();
    int checkGoalState = 0;

    ArrayList<State> leaves = new ArrayList<>();
    ArrayList<State> visited = new ArrayList<>();
    ArrayList<State> returnList = new ArrayList<>();
    int trueCount = 0;

    //method to transfer to arraylist
    for (i = 0; i < initialState.length; i++)
    {
      for (j = 0; j < initialState[0].length; j++)
      {
        checkInversions.add(initialState[i][j]);
        if (initialState[i][j] == 0)
        {
          rowVal = initialState.length - 1 - i;
        }
      }
    }
    //checking inversions
    for (i = 0; i < checkInversions.size() - 1; i++)
    {
      for (j = i + 1; j < checkInversions.size(); j++)
      {
        if (checkInversions.get(i) > checkInversions.get(j) && checkInversions.get(j) != 0)
        {
          inversions++;
        }
      }
    }

    if (initialState.length % 2 == 1)
    {
      if (inversions % 2 == 1)
      {
        returnList.add(new State(true));
        return returnList;
      }
    }
    else if (initialState.length % 2 == 0)
    {
      if ((inversions + rowVal) % 2 == 0)
      {
        returnList.add(new State(true));
        return returnList;
      }
    }

    //Adding tree root
    leaves.add(new State(0, initialState, findHeuristic(initialState, 0), null));

    //LOOP
    while (trueCount != numberOfElements)
    {
      //converting most recently added state to arraylist
      checkAdd = false;

      int[][] checkGoal2D = leaves.get(0).getState();
//      System.out.println(Arrays.deepToString(checkGoal2D));
      //checking if goal
      for (i = 0; i < initialState.length; i++)
      {
        for (j = 0; j < initialState[0].length; j++)
        {
          checkGoal.add(checkGoal2D[i][j]);
        }
      }
      //checking how many elements match
      for (i = 0; i < numberOfElements; i++)
      {
        if (checkGoal.get(i) == i)
        {
          trueCount++;
        }
      }
      //breaking out of while or resetting loop condition
      if (trueCount == numberOfElements)
      {
        break;
      }
      else
      {
        trueCount = 0;
        checkGoal.clear();
      }

      visited.add(leaves.get(0));
      leaves.addAll(findMove(leaves.get(0)));
      leaves.remove(0);

//      for (i = 0; i < returnList.size(); i++)
//      {
//        for (j = 0; j < leaves.size(); j++)
//        {
//          if (returnList.get(i).getHeuristic() < leaves.get(j).getHeuristic())
//          {
//            leaves.add(returnList.get(i));
//          }
//          else if (returnList.get(i).getHeuristic() >= leaves.get(j).getHeuristic())
//          {
//            leaves.add(returnList.get(i));
//          }
//        }
//      }
      State[] movesArray = new State[leaves.size()];

      for (i = 0; i < leaves.size(); i++)
      {
        movesArray[i] = leaves.get(i);
      }

      Arrays.sort(movesArray, Comparator.comparing((State state) -> state.getHeuristic()));

      leaves.clear();

      for (i = 0; i < movesArray.length; i++)
      {
        leaves.add(movesArray[i]);
      }

//      System.out.println("");
//        returnList.subList(k + 1, returnList.size()).clear();
//        returnList.add(returnList.get(returnList.size() - 1).getMoves()[1]);
//        checkAdd = true;
      //OPTIMIZATIONS
      //Checking if visited
    }
    returnList.add(leaves.get(0));
    nextState = leaves.get(0);

    while (checkParent == true)
    {
      if (nextState.getParent() != null)
      {
        returnList.add(0, nextState.getParent());
        nextState = nextState.getParent();
        checkParent = true;
      }
      else
      {
        checkParent = false;
      }
    }
    return returnList;
  }

  public static ArrayList<State> findMove(State parent)
  {
    //variables
    int i;
    int j = 0;
    int temp;
    int numCopies;
    int k;
    int q;
    int m;
    boolean broke = false;
    ArrayList<Integer> xTransforms = new ArrayList<>();
    ArrayList<Integer> yTransforms = new ArrayList<>();
    ArrayList<State> moves = new ArrayList<>();
    //defining working state
    int[][] workingState = parent.getState();
    int[][] parameterState = new int[workingState.length][workingState[0].length];
    //identifying blank position
    for (i = 0; i < workingState.length; i++)
    {
      for (j = 0; j < workingState[0].length; j++)
      {
        if (workingState[i][j] == 0)
        {
          broke = true;
          break;
        }
      }
      if (broke)
      {
        break;
      }
    }
    //identifying possible vertical moves
    if (i == 0)
    {
      xTransforms.add(1);
    }
    else if (i == 2)
    {
      xTransforms.add(-1);
    }
    else if (i == 1)
    {
      xTransforms.add(1);
      xTransforms.add(-1);
    }
    //horizontal
    if (j == 0)
    {
      yTransforms.add(1);
    }
    else if (j == 2)
    {
      yTransforms.add(-1);
    }
    else if (j == 1)
    {
      yTransforms.add(1);
      yTransforms.add(-1);
    }

    //looping through and adding transformed states 
    for (k = 0; k < xTransforms.size(); k++)
    {
      //copying working state to save parent state
      for (q = 0; q < workingState.length; q++)
      {
        for (m = 0; m < workingState.length; m++)
        {
          parameterState[q][m] = workingState[q][m];
        }
      }
      //swapping blank with another space
      temp = parameterState[i + xTransforms.get(k)][j];
      parameterState[i + xTransforms.get(k)][j] = parameterState[i][j];
      parameterState[i][j] = temp;

      if (parent.getLevel() > 0 && xTransforms.get(k) == 1 && Arrays.deepEquals(parameterState, parent.getParent().getState()) != true)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Down"));
      }
      else if (parent.getLevel() == 0 && xTransforms.get(k) == 1)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Down"));
      }
      else if (parent.getLevel() > 0 && xTransforms.get(k) == -1 && Arrays.deepEquals(parameterState, parent.getParent().getState()) != true)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Up"));
      }
      else if (parent.getLevel() == 0 && xTransforms.get(k) == -1)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Up"));
      }

//      if (xTransforms.size() >= 1 && xTransforms.get(k) == 1)
//      {
//        moves.get(moves.size() - 1).setMove("Right");
//      }
//      else if (xTransforms.size() >= 1 && xTransforms.get(k) == -1)
//      {
//        moves.get(moves.size() - 1).setMove("Left");
//      }
    }

    //repeating for horizontal moves
    for (k = 0; k < yTransforms.size(); k++)
    {

      //copying working state to save parent state
      for (q = 0; q < workingState.length; q++)
      {
        for (m = 0; m < workingState.length; m++)
        {
          parameterState[q][m] = workingState[q][m];
        }
      }

      //swapping blank with another space
      temp = parameterState[i][j + yTransforms.get(k)];
      parameterState[i][j + yTransforms.get(k)] = parameterState[i][j];
      parameterState[i][j] = temp;

      if (parent.getLevel() > 0 && yTransforms.get(k) == 1 && Arrays.deepEquals(parameterState, parent.getParent().getState()) != true)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Right"));
      }
      else if (parent.getLevel() == 0 && yTransforms.get(k) == 1)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Right"));
      }
      else if (parent.getLevel() > 0 && yTransforms.get(k) == -1 && Arrays.deepEquals(parameterState, parent.getParent().getState()) != true)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Left"));
      }
      else if (parent.getLevel() == 0 && yTransforms.get(k) == -1)
      {
        moves.add(new State(parent.getLevel() + 1, copy2D(parameterState), findHeuristic(parameterState, parent.getLevel() + 1), parent, "Left"));
      }

//      if (yTransforms.size() >= 1 && yTransforms.get(k) == 1)
//      {
//        moves.get(moves.size() - 1).setMove("Up");
//      }
//      else if (yTransforms.size() >= 1 && yTransforms.get(k) == -1)
//      {
//        moves.get(moves.size() - 1).setMove("Down");
//      }
    }

//    State[] movesArray = new State[moves.size()];
//    for (i = 0; i < moves.size(); i++)
//    {
//      movesArray[i] = moves.get(i);
//    }
//
//    Arrays.sort(movesArray, Comparator.comparing((State state) -> state.getHeuristic()));
//    parent.setMoves(movesArray);
//    for (int p = 0; p < parent.getMoves().length; p++)
//    {
//      System.out.println(Arrays.deepToString(movesArray[p].getState()));
//      System.out.println(movesArray[p].getHeuristic());
//      System.out.println("");
//    }
    //FUNCTIONS
    //Not going back to parent state
//    System.out.println(Arrays.deepToString(movesArray[0].getState()));
    return moves;
  }

  public static int[][] copy2D(int[][] paramArray)
  {
    int[][] localArray = new int[paramArray.length][paramArray[0].length];
    int i;
    int j;
    //transferring
    for (i = 0; i < paramArray.length; i++)
    {
      for (j = 0; j < paramArray[0].length; j++)
      {
        localArray[i][j] = paramArray[i][j];
      }
    }
    return localArray;
  }

  public static int findHeuristic(int[][] paramState, int level)
  {
    int i;
    int j;
    int x1;
    int y1;
    int x2;
    int y2;
    int manhattanDistance = 0;

//    System.out.println(Arrays.deepToString(paramState));
    for (i = 0; i < paramState.length; i++)
    {
      for (j = 0; j < paramState[0].length; j++)
      {
        x1 = i;
        y1 = j;
        x2 = paramState[i][j] / 4;
        y2 = paramState[i][j] % 4;

        manhattanDistance = manhattanDistance + Math.abs(x2 - x1) + Math.abs(y2 - y1);
//        manhattanDistance = manhattanDistance + (Math.abs((paramState[i][j] % 3) - i) + Math.abs((paramState[i][j] / 3) - j));
//        System.out.println(manhattan)
      }
    }

    return manhattanDistance + level;
  }

  public static void main(String[] args)
  {
    int check;
    int i;
    ArrayList<State> finalState;

    int[][] initialState = new int[3][3];
    initialState[0][0] = 8;
    initialState[0][1] = 3;
    initialState[0][2] = 2;
    initialState[1][0] = 4;
    initialState[1][1] = 7;
    initialState[1][2] = 1;
    initialState[2][0] = 0;
    initialState[2][1] = 5;
    initialState[2][2] = 6;

    int[][] testState = new int[4][4];
    testState[0][0] = 1;
    testState[0][1] = 2;
    testState[0][2] = 3;
    testState[0][3] = 0;
    testState[1][0] = 4;
    testState[1][1] = 5;
    testState[1][2] = 6;
    testState[1][3] = 7;
    testState[2][0] = 8;
    testState[2][1] = 9;
    testState[2][2] = 10;
    testState[2][3] = 11;
    testState[3][0] = 12;
    testState[3][1] = 13;
    testState[3][2] = 14;
    testState[3][3] = 15;

    finalState = AStarAlgorithm(testState);

    if (finalState.get(0).getSolvable() == true)
    {
      System.out.println("The puzzle is not solvable");
    }
    else
    {
      System.out.print("Initial State: ");
      System.out.println(Arrays.deepToString(testState));

      System.out.print("Moves: ");
      for (i = 1; i < finalState.size() - 1; i++)
      {
        System.out.print(finalState.get(i).getMove() + ", ");
      }

      System.out.println(finalState.get(finalState.size() - 1).getMove());
    }
  }
}
