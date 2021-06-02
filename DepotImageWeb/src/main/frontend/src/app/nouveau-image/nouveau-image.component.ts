import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { FileUpload } from 'primeng/fileupload';
import { ImageService } from './image.service';
import { Router } from '@angular/router';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-nouveau-image',
  templateUrl: './nouveau-image.component.html',
  styleUrls: ['./nouveau-image.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class NouveauImageComponent implements OnInit {

  block = false;

  checked: boolean = false;
  imageForm:FormGroup;
   publique1:string;
   type:string;



  @ViewChild('pFileUpload') pFileUpload : FileUpload;


  constructor(private fb:FormBuilder, private service: ImageService,private messageService: MessageService , private router: Router) { }

  ngOnInit(): void {
    this.imageForm = this.fb.group({
      titre: ["", [Validators.required]],
      sousTitre: ["", Validators.required],
      description: ["", Validators.required],
      
    });
    this.publiquefn();
  }

  ajouter(){
    this.service.ajouter({
      titre: this.imageForm.get('titre').value,
      sousTitre: this.imageForm.get('sousTitre').value,
      description: this.imageForm.get('description').value,
      type: this.type,
     
      


    }).subscribe((data:any)=>{
      console.log(data);
      console.log(this.type);
     

      this.pFileUpload.url = 'api/document/'+data+'/upload';
      this.pFileUpload.upload();
     





    });

  }
  annuler(){
    this.router.navigate(['/accueil']);
  }

  publiquefn(){
    if (this.checked==true)
    this.publique1='publique';
    else{
      this.publique1='privé';
    }
  }

  onUpload($event) {
    
    console.log('upload done !');
    this.block = false;
    this.messageService.add({severity: 'info', summary: 'File Uploaded', detail: ''});
    this.router.navigate(['/accueil']);
}

typeimg(){
  this.type="image";
}
typefile(){
  this.type="file";
}
}
