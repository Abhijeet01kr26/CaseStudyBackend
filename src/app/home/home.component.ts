import { Component } from '@angular/core';
import { Accessories } from 'src/Data/accessories';
import { menJeans } from 'src/Data/Men/men_jeans';
import { menkurta } from 'src/Data/Men/men_kurta';
import { Shoes } from 'src/Data/shoes';
import { womenJeans } from 'src/Data/Women/womenJean';
import { womenTop } from 'src/Data/Women/womenTop';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  menJeans:any
  womenJeans:any
  womenTop:any
  menKurta:any
  shoes:any
  accessories:any

  ngOnInit(){
    this.menJeans=menJeans.slice(0,5)
    this.womenJeans=womenJeans.slice(0,5)
    this.womenTop=womenTop.slice(0,5)
    this.menKurta=menkurta.slice(0,5)
    this.shoes=Shoes.slice(0,5)
    this.accessories=Accessories.slice(0,5)
  }
}
