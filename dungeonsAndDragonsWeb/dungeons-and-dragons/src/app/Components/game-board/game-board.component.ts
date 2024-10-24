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

  // Initialize the board as a 10x10 grid
  initializeBoard(): void {
    this.board = Array(10).fill(null).map(() => Array(10).fill(null));
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

  // Function to move the player in the given direction
  movePlayer(direction: string): void {
    switch (direction) {
      case 'up':
        if (this.player.y > 0) this.player.y--;
        break;
      case 'down':
        if (this.player.y < 9) this.player.y++;
        break;
      case 'left':
        if (this.player.x > 0) this.player.x--;
        break;
      case 'right':
        if (this.player.x < 9) this.player.x++;
        break;
    }

    this.updateBoard();
    this.sendMoveToServer(direction); // Send the move to the back-end
  }

  // Function to send player move to the back-end
  sendMoveToServer(direction: string): void {
    this.http.post('/api/player/move', { direction })
      .subscribe(response => {
        console.log('Move registered on the server:', response);
      }, error => {
        console.error('Error moving player:', error);
      });
  }

  // Handle key press events for movement (optional)
  handleKeyPress(event: KeyboardEvent): void {
    switch (event.key) {
      case 'ArrowUp':
        this.movePlayer('up');
        break;
      case 'ArrowDown':
        this.movePlayer('down');
        break;
      case 'ArrowLeft':
        this.movePlayer('left');
        break;
      case 'ArrowRight':
        this.movePlayer('right');
        break;
    }
  }
}
