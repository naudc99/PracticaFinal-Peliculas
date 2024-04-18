import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../services/auth/register/registerRequest';
import { RegisterService } from '../../services/auth/register/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerError: string = '';
  registerForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    email:['',[Validators.required,Validators.email]],
  });

  constructor(private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService) { }

  ngOnInit(): void { }

  get username() {
    return this.registerForm.controls.username;
  }

  get email() {
    return this.registerForm.controls.email;
  }

  get password() {
    return this.registerForm.controls.password;
  }

  register() {
    if (this.registerForm.valid) {
      this.registerError = '';
      this.registerService.register(this.registerForm.value as RegisterRequest).subscribe({
        next: (userData) => {
          console.log(userData);
          this.router.navigateByUrl('/inicio');
          this.registerForm.reset();
        },
        error: (errorData) => {
          console.error(errorData);
          this.registerError = 'Error al registrar. Por favor, verifica tus datos.';
        },
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}
