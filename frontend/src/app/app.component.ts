import { Component } from '@angular/core';
import { ProductService } from './services/product.service';
import { Product } from './models/product.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Coffee Shop';
  products: Product[];

  edit_index: Number;
  edit_name: String;

  new_value;

  new_product: Product;

  constructor(private productService: ProductService) {
    this.new_product = new Product();
    this.new_product.name = "";
  }

  ngOnInit() {
    this.productService.getProducts().subscribe(
      data => {
        this.products = data.content;
        this.products.sort(this.compare);
      }
    );
  }

  compare(a, b) {
    if (a.name < b.name)
      return -1;
    if (a.name > b.name)
      return 1;
    return 0;
  }

  createProduct() {
    if(this.new_product.name != undefined && this.new_product.name.trim().length != 0
      && this.new_product.size != undefined
      && this.new_product.price != undefined){
      this.productService.createProduct(this.new_product)
        .subscribe(
          data => {
            let message = data.message;
            let level = data.level;
            if(level == "success") {
              this.new_product.productId = data.id;
              this.products.push(this.new_product);
              this.products.sort(this.compare);
              this.new_product = new Product();
              this.new_product.name = "";
            } else {
              alert(message);
            }
          }
        );
    }
    else {
      alert("Please fill out the required fields.");
    }
  }

  onEditClick(index, name) {
    this.edit_index = index;
    this.edit_name = name;
    if(name == "name") {
      this.new_value = this.products[index].name;
    } else if(name == "size") {
      this.new_value = this.products[index].size;
    } else if(name == "price") {
      this.new_value = this.products[index].price;
    }
  }

  onKeydown(i, name) {
    this.productService.updateProduct(this.products[i].productId, name, this.new_value)
      .subscribe(
        data => {
          let message = data.message;
          let level = data.level;
          if(level == "success") {
            if(name == "name") {
              this.products[i].name = this.new_value;
            } else if(name == "size") {
              this.products[i].size = this.new_value;
            } else if(name == "price") {
              this.products[i].price = this.new_value;
            }
          } else {
            alert(message);
          }
          this.edit_index = -1;
          this.edit_name = "";
          this.new_value = undefined;
        }
      );
  }

  onDelete(index) {
    this.productService.deleteProduct(this.products[index].productId)
      .subscribe(
        data => {
          let message = data.message;
          let level = data.level;
          if(level == "success") {
            this.products.splice(index, 1);
          } else {
            alert(message);
          }
          this.edit_index = -1;
          this.edit_name = "";
          this.new_value = undefined;
        }
      );
  }

}
