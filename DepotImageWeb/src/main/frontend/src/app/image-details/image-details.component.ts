import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceService } from './service.service';
export interface detaille {
  IdDoc:number;
  nom:string;
  prenom:string;
  titre:string;
  soustitre:string;
  description:string;
}

@Component({
  selector: 'app-image-details',
  templateUrl: './image-details.component.html',
  styleUrls: ['./image-details.component.scss'],
  encapsulation: ViewEncapsulation.None
})

export class ImageDetailsComponent implements OnInit {

  idDoc:number;
  detailleDoc:detaille={IdDoc:1,nom:"a",prenom:"b",titre:"c",soustitre:"description",description:"e"};
  constructor(    private router: Router,
    private route: ActivatedRoute , private service:ServiceService) { }
    images: any[];

    responsiveOptions:any[] = [
      {
          breakpoint: '1024px',
          numVisible: 5
      },
      {
          breakpoint: '768px',
          numVisible: 3
      },
      {
          breakpoint: '560px',
          numVisible: 1
      }
  ];
  ngOnInit(): void {
    this.service.getImages().then(images => this.images = images)
    if (this.route.snapshot.paramMap.get('id')) {
      this.idDoc = Number(this.route.snapshot.paramMap.get('id'));
      this.service.detaille_pub(this.idDoc).subscribe((data:any)=>{
        this.detailleDoc=data;
        console.log(data);
        this.service.get_liste_images(this.idDoc).subscribe((data:number[])=>{
          console.log(data);
        })
      })

      // recupertaion des details du doc et l'afficher

    }else{
      this.idDoc = null;
    }

  }

}
