export type ModelState = {
  modelId: number;
  modelName: string;
};

export type ModelAction = {
  type: "SET_MODEL";
  payload: { modelId: number; modelName: string };
};
