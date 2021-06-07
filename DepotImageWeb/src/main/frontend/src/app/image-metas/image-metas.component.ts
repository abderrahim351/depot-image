import { Input } from '@angular/core';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { TreeNode } from 'primeng/api';
import { ImageMetasService } from './image-metas.service';

@Component({
  selector: 'app-image-metas',
  templateUrl: './image-metas.component.html',
  styleUrls: ['./image-metas.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [ImageMetasService],
})
export class ImageMetasComponent implements OnInit {
  private _idImg: number;

  metasTree: TreeNode[];

  constructor(private service: ImageMetasService) {}

  ngOnInit(): void {}

  public get idImg(): number {
    return this._idImg;
  }

  @Input()
  public set idImg(value: number) {
    this._idImg = value;
    this.service.metadatas(this._idImg).then((res) => {
      this.metasTree = res;
    });
  }
}
