import { useCallback } from "react";

const useGet = (url: string) => {
  const get = useCallback(async () => {
    const response = await fetch(url);
    const data = await response.json();

    if (!response.ok) {
      throw new Error(data.message);
    } else {
      return data;
    }
  }, [url]);
  return get;
};

const usePost = (url: string) => {
  const post = useCallback(
    async (body: object) => {
      const response = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
      });
      const data = await response.json();
      if (!response.ok) {
        throw new Error(data.message);
      } else {
        return data;
      }
    },
    [url]
  );
  return post;
};

export { useGet, usePost };
