import { Component,OnInit } from '@angular/core';
import { NgFor,NgIf } from '@angular/common';
import { ApiService } from './api.service';
import { Dashboard,Order,InventoryItem,Shipment } from './models';
@Component({standalone:true,imports:[NgFor,NgIf],template:`
<section class="page-head"><div><span class="eyebrow">LIVE SNAPSHOT</span><h1>Operations overview</h1><p class="muted">Monitor the health of your supply chain in one place.</p></div><button class="btn-secondary" (click)="load()">↻ Refresh data</button></section>
<div class="kpi-grid" *ngIf="summary">
 <article class="kpi"><span class="kpi-icon blue">▤</span><span class="kpi-label">Open orders</span><strong>{{summary.openOrders}}</strong><small>Awaiting completion</small></article>
 <article class="kpi"><span class="kpi-icon amber">▣</span><span class="kpi-label">Low-stock SKUs</span><strong>{{summary.lowStockSkus}}</strong><small>At or below reorder point</small></article>
 <article class="kpi"><span class="kpi-icon violet">⇢</span><span class="kpi-label">Active shipments</span><strong>{{summary.activeShipments}}</strong><small>In logistics pipeline</small></article>
 <article class="kpi"><span class="kpi-icon green">✓</span><span class="kpi-label">Delivered</span><strong>{{summary.deliveredShipments}}</strong><small>Completed shipments</small></article>
</div>
<div class="content-grid">
 <section class="panel"><div class="panel-head"><div><h3>Recent orders</h3><p class="muted">Latest customer activity</p></div></div>
 <div class="table-wrap"><table><thead><tr><th>Order</th><th>Customer</th><th>Status</th></tr></thead><tbody><tr *ngFor="let o of orders"><td>#{{o.id}}</td><td>{{o.customerName}}</td><td><span class="pill">{{o.status}}</span></td></tr></tbody></table></div></section>
 <section class="panel"><div class="panel-head"><div><h3>Inventory watchlist</h3><p class="muted">Items requiring attention</p></div></div>
 <div class="watch-row" *ngFor="let i of lowStock"><div><b>{{i.name}}</b><small>{{i.sku}} · {{i.warehouse}}</small></div><span class="stock-alert">{{i.available}} available</span></div>
 <div class="empty" *ngIf="!lowStock.length">No low-stock items right now.</div></section>
</div>`})
export class DashboardComponent implements OnInit {
 summary:Dashboard|null=null;orders:Order[]=[];lowStock:InventoryItem[]=[];
 constructor(private api:ApiService){}
 ngOnInit(){this.load();}
 load(){this.api.dashboard().subscribe(v=>this.summary=v);this.api.orders().subscribe(v=>this.orders=v.slice(-5).reverse());this.api.inventory().subscribe(v=>this.lowStock=v.filter(i=>i.lowStock));}
}
