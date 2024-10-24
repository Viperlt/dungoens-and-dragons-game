import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { AppComponent } from './app.component';
import { GameBoardComponent } from './Components/game-board/game-board.component';
import { MenuComponent } from './Components/menu/menu.component';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';



@NgModule({
  declarations: [
    AppComponent,
    GameBoardComponent,
    MenuComponent
  ],
  imports: [
    CommonModule,
    RouterOutlet,
    BrowserModule,
  ],
  providers: [
    provideHttpClient()
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
