/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

public class State
{

  private int level;
  private int[][] state;
  private State[] moves;
  private State parent;
  private String move;
  private int heuristic;
  private boolean solvable;

  public State (boolean solvable) {
    this.solvable = solvable;
  }
  //constructor
  public State(int level, int[][] state, int heuristic, State parent)
  {
    this.level = level;
    this.state = state;
    this.heuristic = heuristic;
    this.parent = parent;
  }
  
  public State(int level, int[][] state, int heuristic, State parent, String move)
  {
    this.level = level;
    this.state = state;
    this.heuristic = heuristic;
    this.parent = parent;
    this.move = move;
  }

  //setters
  public void setLevel(int level)
  {
    this.level = level;
  }

  public void setState(int[][] state)
  {
    this.state = state;
  }

  public void setMovePrior(String movePrior)
  {
    this.move = movePrior;
  }

  public void setMoves(State[] moves)
  {
    this.moves = moves;
  }

  public void setParent(State parent)
  {
    this.parent = parent;
  }
  
  public void setMove(String move)
  {
    this.move = move;
  }

  //getters
  public int getLevel()
  {
    return this.level;
  }

  public int[][] getState()
  {
    return this.state;
  }

  public String getMovePrior()
  {
    return this.move;
  }

  public int getHeuristic()
  {
    return this.heuristic;
  }

  public State[] getMoves()
  {
    return this.moves;
  }

  public State getParent()
  {
    return this.parent;
  }
  
  public String getMove()
  {
    return this.move;
  }
  
  public boolean getSolvable() {
    return this.solvable;
  }
}