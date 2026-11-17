import { Injectable,signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';
@Injectable({providedIn:'root'})
export class AuthService {
 token=signal<string|null>(localStorage.getItem('sc360-token'));
 role=signal<string|null>(localStorage.getItem('sc360-role'));
 constructor(private http:HttpClient){}
 login(username:string,password:string){return this.http.post<{token:string;username:string;role:string}>('/api/auth/login',{username,password}).pipe(tap(r=>{localStorage.setItem('sc360-token',r.token);localStorage.setItem('sc360-role',r.role);this.token.set(r.token);this.role.set(r.role);}));}
 logout(){localStorage.removeItem('sc360-token');localStorage.removeItem('sc360-role');this.token.set(null);this.role.set(null);}
}
