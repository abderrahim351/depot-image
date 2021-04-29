import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { FileUpload } from 'primeng/fileupload';
import { ImageService } from './image.service';

@Component({
  selector: 'app-nouveau-image',
  templateUrl: './nouveau-image.component.html',
  styleUrls: ['./nouveau-image.component.scss']
})
export class NouveauImageComponent implements OnInit {

  block = false;

  checked: boolean = false;
  imageForm:FormGroup;
   publique1:string;
   f1:File;
   uploadedFiles: any[] = [];
  b:boolean=false;
  formdata1;

  @ViewChild('pFileUpload') pFileUpload : FileUpload;


  constructor(private fb:FormBuilder, private service: ImageService,private messageService: MessageService ) { }

  ngOnInit(): void {
    this.imageForm = this.fb.group({
      titre: ["", [Validators.required, Validators.email]],
      sousTitre: ["", Validators.required],
      description: ["", Validators.required],
      publique: [false, Validators.required],


    });
    this.publiquefn();
  }

  ajouter(){

    this.block = true;

    console.log(this.imageForm.get('publique').value);

    this.service.ajouter({
      titre: this.imageForm.get('titre').value,
      sousTitre: this.imageForm.get('sousTitre').value,
      description: this.imageForm.get('description').value,
      publique: this.imageForm.get('publique').value,


    }).subscribe((data:any)=>{
      console.log(data);

      this.pFileUpload.url = 'api/document/'+data+'/upload';

      this.pFileUpload.upload();

      console.log("document ajouter");

    });
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
}

}
