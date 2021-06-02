import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  ImageDetailsModel,
  ImageDetailsService,
} from './image-details.service';
export interface detaille {
  IdDoc: number;
  nom: string;
  prenom: string;
  titre: string;
  soustitre: string;
  description: string;
}
export interface metadata {
  Id: number;
  tag: string;
  valeur: string;
}

@Component({
  selector: 'app-image-details',
  templateUrl: './image-details.component.html',
  styleUrls: ['./image-details.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ImageDetailsComponent implements OnInit {

  idDoc: number;
  detailleDoc: ImageDetailsModel;
  display: boolean;
  images: any[];
  metadata:metadata[];

  responsiveOptions: any[] = [
    {
      breakpoint: '1024px',
      numVisible: 5,
    },
    {
      breakpoint: '768px',
      numVisible: 3,
    },
    {
      breakpoint: '560px',
      numVisible: 1,
    },
  ];
  src: string;
  

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: ImageDetailsService
  ) {}

  ngOnInit(): void {
    if (this.route.snapshot.paramMap.get('id')) {
      this.idDoc = Number(this.route.snapshot.paramMap.get('id'));
      this.service
        .detaillePub(this.idDoc)
        .subscribe((data: ImageDetailsModel) => {
          this.detailleDoc = data;
          console.log('result ' + JSON.stringify(data));
        });

      // recupertaion des details du doc et l'afficher
    } else {
      this.idDoc = null;
      this.detailleDoc = null;
    }

  }
  detaille(i : number){
    console.log(i);
    
    this.display=true;
    this.src="api/img/"+i;
    console.log(this.src);
    this.service.getMetadata(i).subscribe((data:any[])=>{
     
      this.metadata=data;
      console.log(this.metadata);
    })
  }
  
}
