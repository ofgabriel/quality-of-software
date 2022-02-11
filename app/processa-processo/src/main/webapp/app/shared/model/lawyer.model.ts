export interface ILawyer {
  id?: number;
  name?: string | null;
}

export class Lawyer implements ILawyer {
  constructor(public id?: number, public name?: string | null) {}
}
