import { Component,OnInit } from '@angular/core';
import { NgFor,NgIf } from '@angular/common';
import { ApiService } from './api.service';
import { InventoryItem } from './models';
@Component({standalone:true,imports:[NgFor,NgIf],template:`
<section class="page-head"><div><span class="eyebrow">WAREHOUSE CONTROL</span><h1>Inventory</h1><p class="muted">Available stock reflects active order reservations.</p></div><button class="btn-secondary" (click)="load()">↻ Refresh</button></section>
<section class="panel"><div class="table-wrap"><table><thead><tr><th>SKU / Product</th><th>Warehouse</th><th>On hand</th><th>Reserved</th><th>Available</th><th>Reorder point</th><th>Health</th></tr></thead><tbody><tr *ngFor="let i of items"><td><b>{{i.sku}}</b><small>{{i.name}}</small></td><td>{{i.warehouse}}</td><td>{{i.onHand}}</td><td>{{i.reserved}}</td><td><b>{{i.available}}</b></td><td>{{i.reorderPoint}}</td><td><span [class]="i.lowStock?'pill danger':'pill success'">{{i.lowStock?'Reorder':'Healthy'}}</span></td></tr></tbody></table></div></section>`})
export class InventoryComponent implements OnInit {
 items:InventoryItem[]=[];constructor(private api:ApiService){}
 ngOnInit(){this.load();}load(){this.api.inventory().subscribe(v=>this.items=v);}
}
