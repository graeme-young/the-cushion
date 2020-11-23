import {
  Link as ChakraLink,
  Text,
  Code,
  Icon,
  List,
  ListIcon,
  ListItem,
  Box,
  Image,
  Flex,
  Heading,
  Link,
} from "@chakra-ui/core";
import axios from "axios";
import { useEffect, useState } from "react";
import { useInfiniteQuery } from "react-query";
import { useQuery } from "react-query";
import { Item } from "../../types";
import { ItemPreviewCard } from "../components/ItemPreviewCard";
import { Navbar } from "../components/Navbar";

const Index = () => {
  const { data, isLoading, error, isError } = useQuery(
    "fetchItems",
    async () => {
      return await axios.get("http://localhost:8080/api/items");
    }
  );

  // useEffect(() => {}, [data]);

  if (isLoading) {
    return <p>...Loading</p>;
  }

  if (isError) {
    return (
      <>
        <p>There was an error loading items, please try again</p>
        <p>{error}</p>
      </>
    );
  }

  if (data) {
    if (data.data.length > 3) {
      data.data.slice();
    }
  }

  return (
    <>
      <Navbar />
      <Box ml={15}>
        <Heading>Welcome to the Cushion!</Heading>
        <Text>The world's greatest fake furntiure store</Text>
        <Text>Browse fake couches, chairs, tables and more!</Text>
        <Text>
          <Link>Log in</Link> to add items to your wishlist, shopping cart and
          pretend to buy any of the items in our store!
        </Text>
      </Box>
      <Flex m={25} flexDir="row">
        {data.data.map((item) => {
          return <ItemPreviewCard item={item as Item} key={item.itemId} />;
        })}
      </Flex>
    </>
  );
};

export default Index;
