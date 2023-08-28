import { useState, useEffect } from "react";

interface FetchResult<T> {
  data: T | null;
  loading: boolean;
  error: Error | null;
}

type HttpMethod = "GET" | "POST" | "PATCH";

interface FetchOptions {
  method: HttpMethod;
  body?: object;
  headers?: Record<string, string>;
}

function useFetch<T>(
  url: string,
  options: FetchOptions = { method: "GET" }
): FetchResult<T> {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<Error | null>(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const cache = await caches.open("my-car-master-cache");
        const cacheResponse = await cache.match(url);

        if (cacheResponse) {
          const cachedData: T = await cacheResponse.json();
          setData(cachedData);
          return { data };
        }

        const response = await fetch(url, {
          method: options.method,
          body: options.body ? JSON.stringify(options.body) : undefined,
          headers: options.headers || { "Content-Type": "application/json" },
        });

        if (!response.ok) {
          throw new Error("Network response was not ok");
        }

        const responseData: T = await response.json();
        setData(responseData);

        cache.add(url);
      } catch (err) {
        setError(err as Error);
      } finally {
        setLoading(false);
      }
    }

    fetchData();
  }, [url]);

  return { data, loading, error };
}

export default useFetch;
