export async function post(url: string, body: object) {
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
    const json = await response.json();
    return json;
  } catch (error) {
    throw Error(error as string);
  }
}

export async function get(url: string) {
  try {
    const response = await fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const json = await response.json();
    return json;
  } catch (error) {
    throw Error(error as string);
  }
}
