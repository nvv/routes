package com.routes.graph.model

import java.util.*

/**
 * @author Vlad Namashko
 */
class Graph<T, E>(
        val vertices: MutableMap<T, Vertex<T>> = mutableMapOf(),
        val vertexEdges: MutableMap<Vertex<T>, LinkedList<Edge<T, E>>> = mutableMapOf()
) {

    private val NEWLINE = System.getProperty("line.separator")

    private var edgeCount = 0

    fun putEdge(vertexName1: T, vertexName2: T, edgeInfo: E) {
        val vertex1 = getVertex(vertexName1)
        val vertex2 = getVertex(vertexName2)

        var vertexEdges = vertexEdges[vertex1]
        if (vertexEdges == null) {
            vertexEdges = LinkedList()
            this.vertexEdges[vertex1] = vertexEdges
        }

        vertexEdges.add(Edge(vertex1, vertex2, edgeInfo))

        edgeCount++
    }

    fun getVertex(key: T) =
            if (vertices.containsKey(key)) {
                vertices[key] ?: Vertex(key)
            } else {
                val vertex = Vertex(key)
                vertices[key] = vertex
                vertex
            }

    fun getEdges(vertex: T): Iterable<Edge<T, E>> {
        return vertexEdges[getVertex(vertex)] ?: emptyList()
    }

    override fun toString(): String {
        val s = StringBuilder()
        s.append("${vertices.size} vertices, $edgeCount edges $NEWLINE")
        vertices.forEach { v ->
            s.append("${v.value.vertex}: ")
            for (w in getEdges(v.value.vertex)) {
                s.append("${w.to} ")
            }
            s.append(NEWLINE)
        }

        return s.toString()
    }
}
