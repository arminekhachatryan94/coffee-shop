import { Component, HostListener, Input } from '@angular/core';
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

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productService.getProducts().subscribe(
      data => {
        this.products = data.content;
      }
    );
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
