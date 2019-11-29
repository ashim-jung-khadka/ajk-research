import {QuestionBase} from './question-base';

export class TextboxQuestion extends QuestionBase {
  controlType = 'textbox';
  type: string;

  constructor(options: any = {}) {
    super(options);
    this.type = options.type || 'text';
  }
}
