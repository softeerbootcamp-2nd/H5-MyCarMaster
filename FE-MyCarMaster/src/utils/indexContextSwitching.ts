export default function indexContextSwitching(name: string): number {
  switch (name) {
    case "트림":
      return 0;
    case "세부모델":
      return 1;
    case "색상":
      return 2;
    case "옵션":
      return 3;
    default:
      return 4;
  }
}
