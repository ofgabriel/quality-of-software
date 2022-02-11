export interface IGroup {
  id?: number;
  name?: string | null;
}

export class Group implements IGroup {
  constructor(public id?: number, public name?: string | null) {}
}
