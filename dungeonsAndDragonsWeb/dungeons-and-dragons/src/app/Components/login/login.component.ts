import { Component } from '@angular/core';
import { AuthService } from '../../Services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.username, this.password).subscribe(
      (response: any) => {
        // Store the JWT token in localStorage
        localStorage.setItem('jwtToken', response.token);
        console.log('Login successful, token stored.');
        
        // // Navigate to the desired page after login
        // this.router.navigate(['/game-board']); // Replace '/dashboard' with your desired route
      },
      (error) => {
        console.error('Login failed:', error);
        // Handle login error (e.g., display error message)
      }
    );
  }

  onRegister() {
    this.router.navigate(['/register']);
  }

}
