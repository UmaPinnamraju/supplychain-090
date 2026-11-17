import { Component,OnInit } from '@angular/core';
import { NgFor,NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from './api.service';
import { Shipment } from './models';
@Component({standalone:true,imports:[NgFor,NgIf,FormsModule],template:`
<section class="page-head"><div><span class="eyebrow">LOGISTICS EXECUTION</span><h1>Shipment tracking</h1><p class="muted">Track fulfillment milestones generated from order events.</p></div><button class="btn-secondary" (click)="load()">↻ Refresh</button></section>
<section class="panel"><div class="table-wrap"><table><thead><tr><th>Tracking</th><th>Order</th><th>Customer</th><th>Carrier</th><th>Current status</th><th>Update status</th></tr></thead><tbody><tr *ngFor="let s of shipments"><td><b>{{s.trackingNumber}}</b></td><td>#{{s.orderId}}</td><td>{{s.customerName}}</td><td>{{s.carrier}}</td><td><span class="pill">{{s.status}}</span></td><td><select #status><option>LABEL_CREATED</option><option>PICKED_UP</option><option>IN_TRANSIT</option><option>OUT_FOR_DELIVERY</option><option>DELIVERED</option><option>EXCEPTION</option></select><button class="btn-small" (click)="update(s.id,status.value)">Save</button></td></tr></tbody></table></div><p class="muted" *ngIf="!shipments.length">Create an order to trigger automatic shipment creation through Kafka.</p></section>`})
export class ShipmentsComponent implements OnInit {
 shipments:Shipment[]=[];constructor(private api:ApiService){}
 ngOnInit(){this.load();}load(){this.api.shipments().subscribe(v=>this.shipments=v);}
 update(id:number,status:string){this.api.updateShipment(id,status,'Updated from operations dashboard').subscribe(()=>this.load());}
}
