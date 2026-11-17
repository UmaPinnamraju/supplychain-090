import { Component } from '@angular/core';
import { RouterLink,RouterOutlet,Router } from '@angular/router';
import { NgIf } from '@angular/common';
import { AuthService } from './auth.service';
@Component({selector:'app-root',standalone:true,imports:[RouterLink,RouterOutlet,NgIf],template:`
<div class="shell">
 <aside class="sidebar">
  <div class="brand"><span class="brand-mark">S</span><div><b>SupplyChain</b><small>360 OPERATIONS</small></div></div>
  <nav>
   <a routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{exact:true}">▦ Overview</a>
   <a routerLink="/orders" routerLinkActive="active">▤ Orders</a>
   <a routerLink="/inventory" routerLinkActive="active">▣ Inventory</a>
   <a routerLink="/shipments" routerLinkActive="active">⇢ Shipments</a>
   <a routerLink="/suppliers" routerLinkActive="active">⌂ Suppliers</a>
  </nav>
  <div class="sidebar-foot"><span class="status-dot"></span> All systems operational</div>
 </aside>
 <main class="main">
  <header class="topbar"><div><span class="eyebrow">OPERATIONS CENTER</span><h2>Supply chain workspace</h2></div>
   <div class="user-area" *ngIf="auth.token()"><span class="avatar">U</span><span>{{auth.role()}}</span><button class="btn-quiet" (click)="logout()">Sign out</button></div>
  </header>
  <router-outlet></router-outlet>
  <footer>SupplyChain 360 <span>Portfolio demonstration · Java / Angular / SQL Server / Kafka</span></footer>
 </main>
</div>`})
export class AppComponent {
 constructor(public auth:AuthService,private router:Router){}
 logout(){this.auth.logout();this.router.navigate(['/login']);}
}
