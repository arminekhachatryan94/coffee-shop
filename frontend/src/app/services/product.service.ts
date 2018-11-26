import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
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
        map((response: Response) => response.json())
      );
  }

  updateProduct(id, name, new_value): Observable<any> {
    console.log(id + " " + name + " " + new_value);
    const headers = new Headers({'Content-Type': 'application/json'});
    let product = String();
    if(name == "name") {
      product = JSON.stringify({name: new_value});
    } else if(name == "size") {
      product = JSON.stringify({size: new_value});
    } else if(name == "price") {
      product = JSON.stringify({price: new_value});
    }
    
    return this.http.put(this.updateProductUrl + id + "/" + name, product, {headers: headers})
      .pipe(
        map( res => res.json() )
      );
  }

  deleteProduct(product_id): Observable<any> {
    return this.http.delete(this.deleteProductUrl + product_id)
      .pipe(
        map((response: Response) => response.json())
      );
  }
}
