import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import { Product } from '../models/Product.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  allPostsUrl = "http://localhost:8080/products";

  constructor(private http: Http) { }

  getProducts(): Observable<any>{
    return this.http.get(this.allPostsUrl)
    .pipe(
      map(res => res.json()) // or any other operator
    )
}

}
