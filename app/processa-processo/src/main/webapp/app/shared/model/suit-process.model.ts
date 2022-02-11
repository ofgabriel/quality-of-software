import { ISuit } from '@/shared/model/suit.model';

export interface ISuitProcess {
  id?: number;
  processInstance?: any | null;
  suit?: ISuit | null;
}

export class SuitProcess implements ISuitProcess {
  constructor(public id?: number, public processInstance?: any | null, public suit?: ISuit | null) {}
}
