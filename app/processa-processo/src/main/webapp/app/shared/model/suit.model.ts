import { ILawyer } from '@/shared/model/lawyer.model';
import { IGroup } from '@/shared/model/group.model';

export interface ISuit {
  id?: number;
  suitNumber?: string | null;
  name?: string | null;
  date?: Date | null;
  claimed?: string | null;
  lawyer?: string | null;
  court?: string | null;
  value?: string | null;
  shouldGroup?: boolean | null;
  shouldReceiveNotification?: boolean | null;
  watcher?: ILawyer | null;
  group?: IGroup | null;
}

export class Suit implements ISuit {
  constructor(
    public id?: number,
    public suitNumber?: string | null,
    public name?: string | null,
    public date?: Date | null,
    public claimed?: string | null,
    public lawyer?: string | null,
    public court?: string | null,
    public value?: string | null,
    public shouldGroup?: boolean | null,
    public shouldReceiveNotification?: boolean | null,
    public watcher?: ILawyer | null,
    public group?: IGroup | null
  ) {
    this.shouldGroup = this.shouldGroup ?? false;
    this.shouldReceiveNotification = this.shouldReceiveNotification ?? false;
  }
}
