import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  allPostsUrl = "http://localhost:8080/products";

  constructor(private http: Http) { }

  getProducts(): Observable<any>{
		return this.http.get(this.allPostsUrl);
	}

}
