export interface ISuit {
  id?: number;
  suitNumber?: string | null;
}

export class Suit implements ISuit {
  constructor(public id?: number, public suitNumber?: string | null) {}
}
