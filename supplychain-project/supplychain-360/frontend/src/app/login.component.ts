import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
@Component({standalone:true,imports:[FormsModule,NgIf],template:`
<section class="login-wrap"><div class="login-card"><div class="brand"><span class="brand-mark">S</span><div><b>SupplyChain 360</b><small>OPERATIONS PLATFORM</small></div></div>
<h1>Welcome back</h1><p class="muted">Sign in to manage orders, inventory and logistics.</p>
<form (ngSubmit)="submit()"><label>Username</label><input name="username" [(ngModel)]="username" required>
<label>Password</label><input name="password" type="password" [(ngModel)]="password" required>
<p class="error" *ngIf="error">{{error}}</p><button class="btn-primary full">Sign in</button></form>
<div class="demo">Demo: admin / Admin@123</div></div></section>`})
export class LoginComponent {
 username='admin';password='Admin@123';error='';
 constructor(private auth:AuthService,private router:Router){}
 submit(){this.auth.login(this.username,this.password).subscribe({next:()=>this.router.navigate(['/']),error:()=>this.error='Unable to sign in. Check your credentials and API status.'});}
}
