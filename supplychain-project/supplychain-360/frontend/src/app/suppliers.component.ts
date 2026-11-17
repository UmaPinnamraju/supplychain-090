import { Component,OnInit } from '@angular/core';
import { NgFor } from '@angular/common';
import { ApiService } from './api.service';
import { Supplier } from './models';
@Component({standalone:true,imports:[NgFor],template:`
<section class="page-head"><div><span class="eyebrow">PROCUREMENT NETWORK</span><h1>Suppliers</h1><p class="muted">Supplier directory for inventory replenishment planning.</p></div></section>
<section class="supplier-grid"><article class="panel supplier-card" *ngFor="let s of suppliers"><span class="supplier-icon">⌂</span><h3>{{s.name}}</h3><p class="muted">{{s.code}}</p><div class="supplier-meta"><span>Lead time</span><b>{{s.leadTime}}</b></div><div class="supplier-meta"><span>Contact</span><b>{{s.contactEmail}}</b></div><span class="pill success">Active supplier</span></article></section>`})
export class SuppliersComponent implements OnInit {
 suppliers:Supplier[]=[];constructor(private api:ApiService){}
 ngOnInit(){this.api.suppliers().subscribe(v=>this.suppliers=v);}
}
