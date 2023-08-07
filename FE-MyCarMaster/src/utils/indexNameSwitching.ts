export default function indexNameSwitching(
  name: string | number
): number[] | string {
  if (typeof name === "number") {
    switch (name) {
      case 0:
        return "트림";
      case 1:
        return "엔진";
      case 2:
        return "구동방식";
      case 3:
        return "바디타입";
      case 4:
        return "외장 색상";
      case 5:
        return "내장 색상";
      case 6:
        return "옵션";
      default:
        return "";
    }
  } else {
    switch (name) {
      case "트림":
        return [0, 0];
      case "세부모델":
        return [1, 3];
      case "색상":
        return [4, 5];
      case "옵션":
        return [6, 6];
      case "견적서 완성":
        return [7, 7];
      default:
        return [0, 0];
    }
  }
}
