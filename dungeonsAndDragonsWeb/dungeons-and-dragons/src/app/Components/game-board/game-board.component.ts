import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-game-board',
  templateUrl: './game-board.component.html',
  styleUrl: './game-board.component.css'
})
export class GameBoardComponent {
  board: any[] = []; // The grid representing the game board
  player = { x: 0, y: 0 }; // Initial position of the player

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.initializeBoard();
  }

  // Initialize the board as a 24x24 grid
  initializeBoard(): void {
    this.board = Array(24).fill(null).map(() => Array(24).fill(null));
    this.updateBoard();
  }

  // Update the player's position on the board
  updateBoard(): void {
    this.board = this.board.map((row, rowIndex) =>
      row.map((cell: number, colIndex: number) => {
        if (rowIndex === this.player.y && colIndex === this.player.x) {
          return 'P'; // Mark the player's position on the board
        }
        return ''; // Empty space
      })
    );
  }

}
