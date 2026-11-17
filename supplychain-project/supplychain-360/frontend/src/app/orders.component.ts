import { Component,OnInit } from '@angular/core';
import { NgFor,NgIf,DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from './api.service';
import { Order } from './models';
@Component({standalone:true,imports:[NgFor,NgIf,DatePipe,FormsModule],template:`
<section class="page-head"><div><span class="eyebrow">ORDER MANAGEMENT</span><h1>Customer orders</h1><p class="muted">Create orders and monitor their lifecycle.</p></div></section>
<div class="panel form-panel"><h3>Create new order</h3>
<div class="form-grid"><label>Customer name<input [(ngModel)]="customerName" placeholder="Northwind Distribution"></label><label>Customer email<input [(ngModel)]="customerEmail" placeholder="ops@example.com"></label><label class="wide">Shipping address<input [(ngModel)]="address" placeholder="Street, city, state"></label>
<label>SKU<select [(ngModel)]="sku"><option>PUMP-100</option><option>VALVE-200</option><option>FILTER-300</option><option>METER-400</option></select></label><label>Quantity<input type="number" min="1" [(ngModel)]="quantity"></label></div>
<p class="error" *ngIf="error">{{error}}</p><button class="btn-primary" (click)="create()">Create order & reserve stock</button>
</div>
<section class="panel"><div class="panel-head"><h3>Order register</h3><span class="muted">{{orders.length}} records</span></div><div class="table-wrap"><table><thead><tr><th>Order</th><th>Customer</th><th>Items</th><th>Created</th><th>Status</th></tr></thead><tbody><tr *ngFor="let o of orders"><td>#{{o.id}}</td><td>{{o.customerName}}<small>{{o.customerEmail}}</small></td><td>{{o.lines.length}} line(s)</td><td>{{o.createdAt | date:'mediumDate'}}</td><td><span class="pill">{{o.status}}</span></td></tr></tbody></table></div></section>`})
export class OrdersComponent implements OnInit {
 orders:Order[]=[];customerName='';customerEmail='';address='';sku='PUMP-100';quantity=1;error='';
 constructor(private api:ApiService){}
 ngOnInit(){this.load();}
 load(){this.api.orders().subscribe(v=>this.orders=v);}
 create(){this.error='';this.api.createOrder({customerName:this.customerName,customerEmail:this.customerEmail,shippingAddress:this.address,lines:[{sku:this.sku,quantity:Number(this.quantity)}]}).subscribe({next:()=>{this.customerName='';this.customerEmail='';this.address='';this.load();},error:e=>this.error=e.error?.message||'Unable to create order'});}
}
