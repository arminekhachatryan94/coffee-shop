import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  getProductsUrl = "http://localhost:8080/products";
  createProductUrl = "http://localhost:8080/products/create";
  updateProductUrl = "http://localhost:8080/products/update/";
  deleteProductUrl = "http://localhost:8080/products/delete/"

  constructor(private http: Http) { }

  getProducts(): Observable<any>{
    return this.http.get(this.getProductsUrl)
      .pipe(
        map(res => res.json())
      );
  }

  deleteProduct(product_id): Observable<any> {
    return this.http.delete(this.deleteProductUrl + product_id)
      .pipe(
        map(res => res.json())
      );
  }
}
