import { Component } from '@angular/core';
import { AuthService } from '../../Services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  register() {
    this.authService.register(this.username, this.password).subscribe(
      response => console.log(response),
      error => console.error(error)
    );
  }
}
