import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { provideRouter, RouterOutlet } from '@angular/router';
import { GameBoardComponent } from './Components/game-board/game-board.component';
import { MenuComponent } from './Components/menu/menu.component';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, provideHttpClient } from '@angular/common/http';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { FormsModule } from '@angular/forms';
import { routes } from './app.routes';
import { AppComponent } from './app.component';
import { AuthInterceptor } from './Services/auth-interceptor.service';



@NgModule({
  declarations: [
    AppComponent,
    GameBoardComponent,
    MenuComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    CommonModule,
    RouterOutlet,
    BrowserModule,
    FormsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    provideHttpClient(),
    provideRouter(routes),
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
